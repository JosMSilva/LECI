
#include <stdio.h>
#include <inttypes.h>
#include "sdkconfig.h"
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "esp_chip_info.h"
#include "esp_flash.h"
#include "esp_system.h"
#include "esp_idf_version.h"
#include "esp_log.h"
#include "driver/i2c_master.h"
#include "common.h"
#include "mqtt.h"
#include "esp_sleep.h"
#include "led_controller.h"
#include "esp_pm.h"
#include "sdcard_manager.h" 
#include "mqtt.h"

/************************ I2C Pin Config ************************/
#define I2C_MASTER_SCL_IO           7
#define I2C_MASTER_SDA_IO           6
#define I2C_MASTER_NUM              I2C_NUM_0
#define I2C_MASTER_FREQ_HZ          100000
#define I2C_MASTER_TX_BUF_DISABLE   0
#define I2C_MASTER_RX_BUF_DISABLE   0

/************************ DHT20 Pin Config ************************/
#define DHT20_ADDR          0x38
#define DHT20_CMD_TRIGGER   {0xAC , 0x33 , 0x00}
#define DHT20_WAIT_MS       100
#define DHT20_READ_BYTES    7
static const char *TAG = "DHT20";
static uint32_t g_sensor_read_period_ms;

/************************ External Data for Task Notification ************************/
extern TaskHandle_t xDisplayTaskHandle;
extern TaskHandle_t xCardTaskHandle;
TaskHandle_t xDhtTaskHandle = NULL;
static sensor_data_t g_last_reading;
sensor_data_t dht20_last_reading(void) {
    return g_last_reading;
}

/************************ DHT20 Main Config ************************/
void dht20_sensor(void *na){

    /************ I2C Master Init ************/
    i2c_master_bus_handle_t bus_handle;
    i2c_master_dev_handle_t dev_handle;
    i2c_master_bus_config_t i2c_bus_conf = {
        .i2c_port = I2C_MASTER_NUM,
        .sda_io_num = I2C_MASTER_SDA_IO,
        .scl_io_num = I2C_MASTER_SCL_IO,
        .clk_source = I2C_CLK_SRC_DEFAULT,
        .glitch_ignore_cnt = 7,
        .flags = {.enable_internal_pullup = false}
    };
    ESP_ERROR_CHECK(i2c_new_master_bus(&i2c_bus_conf , &bus_handle));

    /************ DHT20 Master Init ************/
    i2c_device_config_t DHT20_cfg = {
        .dev_addr_length = I2C_ADDR_BIT_LEN_7,
        .device_address = DHT20_ADDR,
        .scl_speed_hz = I2C_MASTER_FREQ_HZ,
    };
    ESP_ERROR_CHECK(i2c_master_bus_add_device(bus_handle , &DHT20_cfg , &dev_handle));

    /************ Wait for Card Reading ************/
    uint32_t received_period = ulTaskNotifyTake(pdTRUE, portMAX_DELAY);
    if (received_period > 0) {
        if(received_period < 1000){
            g_sensor_read_period_ms = 1000;
        }
        else if(received_period > 3600000){
            g_sensor_read_period_ms = 3600000;
        }
        else{
            g_sensor_read_period_ms = received_period;
        }
    }else{
        g_sensor_read_period_ms = 5000;       
    }
    vTaskDelay(pdMS_TO_TICKS(1000));

    /************ DHT20 Master Start ************/
    uint8_t data_buf[DHT20_READ_BYTES];
    uint8_t cmd_trigger[] = DHT20_CMD_TRIGGER;
    esp_err_t err;

    while(1){

        /** Check I2C State **/
        err = i2c_master_transmit(dev_handle , cmd_trigger , sizeof(cmd_trigger) , -1);

        /** Trigger Failed **/
        if(err != ESP_OK){
            ESP_LOGE(TAG , "Falha ao enviar trigger I2C: %s" , esp_err_to_name(err));
            led_set_error(ERR_SENSOR, true);  
        }
        /** ESP Ready **/
        else{

            /** Reading Delay **/
            vTaskDelay(pdMS_TO_TICKS(DHT20_WAIT_MS));

            /************ Reading Start ************/
            err = i2c_master_receive(dev_handle , data_buf , DHT20_READ_BYTES , -1);
            if(err != ESP_OK){
                ESP_LOGE(TAG , "Falha a ler temperatura: %s" , esp_err_to_name(err));
                led_set_error(ERR_SENSOR, true);              
            }
            else{

                /** Sensor Error Handling **/
                if((data_buf[0] & 0x80) != 0){
                    ESP_LOGW(TAG , "Sensor ocupado, a tentar de novo...");
                    vTaskDelay(pdMS_TO_TICKS(DHT20_WAIT_MS));
                    led_set_error(ERR_SENSOR, true);
                    continue;
                }

                if((data_buf[0] & 0x08) == 0){
                    ESP_LOGW(TAG , "Sensor nao calibrado");
                    led_set_error(ERR_SENSOR, true);
                    continue;
                }

                /** Humidity and Temprature Data Struct **/
                uint32_t raw_humidity = ((uint32_t)data_buf[1] << 12) |
                                        ((uint32_t)data_buf[2] << 4) |
                                        ((data_buf[3] & 0xF0) >> 4);

                float humidity = (float)raw_humidity * 100.0 / (float)(1 << 20);

                uint32_t raw_temp = (((uint32_t)data_buf[3] & 0x0F) << 16) |
                                    ((uint32_t)data_buf[4] << 8) |
                                    data_buf[5];

                float temperature = (float)raw_temp * 200.0 / (float)(1 << 20) - 50.0;

                /** Data Visualization and Sending **/
                sensor_data_t data = {
                    .temperature = temperature,
                    .humidity = humidity,
                    .timestamp = esp_log_timestamp(), 
                };

                g_last_reading = data;

                if (xDisplayTaskHandle != NULL) xTaskNotifyGive(xDisplayTaskHandle);

                if (xCardTaskHandle != NULL) xTaskNotifyGive(xCardTaskHandle);

                if (xMqttTaskHandle != NULL) xTaskNotifyGive(xMqttTaskHandle);

                led_set_error(ERR_SENSOR, false);
            }
        }
        
        vTaskDelay(pdMS_TO_TICKS(g_sensor_read_period_ms));
        
    }
}