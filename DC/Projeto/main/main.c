#include <stdio.h>
#include "esp_log.h"
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "freertos/queue.h"
#include "driver/spi_master.h" 
#include "common.h"
#include "led_controller.h"
#include "dht20_sensor.h"
#include "display.h"
#include "sdcard_manager.h"
#include "wifi_manager.h"
#include "nvs_flash.h"
#include "mqtt.h"
#include "esp_pm.h"
#include "esp_vfs_fat.h"
#include "esp_pm.h"

/************************ PINS and Config ************************/
#define TFT_HOST    SPI2_HOST
#define PIN_MOSI 19
#define PIN_MISO 20  
#define PIN_CLK  21
#define PIN_CS_TFT 22
#define PIN_DC   2
#define PIN_RST  3
#define PIN_BL   15
#define PIN_CS_SD 18 
#define MOUNT_POINT "/sdcard"
static const char *TAG = "APP_MAIN";
esp_pm_lock_handle_t s_pwr_lock = NULL;

/************************ Task Communication ************************/
extern TaskHandle_t xDhtTaskHandle;

/************************ SPI BUS Initialization ************************/
static esp_err_t spi_bus_init_card(void){
    esp_err_t ret;

    /************ SPI Bus Config ************/
    spi_bus_config_t bus_cfg = {
        .mosi_io_num = PIN_MOSI,
        .miso_io_num = PIN_MISO,
        .sclk_io_num = PIN_CLK,
        .quadwp_io_num = -1,
        .quadhd_io_num = -1,
        .max_transfer_sz = 4000,
    };

    /************ SPI Bus Initialization ************/
    ret = spi_bus_initialize(SPI2_HOST, &bus_cfg, SPI_DMA_CH_AUTO);
    if (ret != ESP_OK) {
        ESP_LOGE(TAG, "Falha fatal ao iniciar SPI Bus: %s", esp_err_to_name(ret));
        led_set_error(ERR_SD, true);
        return ret;
    }

    return ESP_OK;
}

/************************ Display Configuration ************************/
static void display_init_attached(void) {

    /************ Joins Display to SPI Bus ************/
    st7735_config_t cfg = {
       .mosi_io_num = PIN_MOSI,
       .sclk_io_num = PIN_CLK,
       .cs_io_num = PIN_CS_TFT,
       .dc_io_num = PIN_DC,
       .rst_io_num = PIN_RST,
       .bl_io_num = PIN_BL,
       .host_id = SPI2_HOST
    };

    /************ Display Initialization ************/
    if (st7735_init(&cfg) != ESP_OK) {
        ESP_LOGE(TAG, "Falha critica na inicializacao do display!");
        led_set_error(ERR_DISPLAY, true);
        return;
    }
    
    st7735_set_rotation(3);
}

/************************ Main Configuration ************************/
void app_main(void) {

    #if CONFIG_PM_ENABLE
        esp_pm_config_t pm_config = {
            .max_freq_mhz = 160,
            .min_freq_mhz = 40,         
            .light_sleep_enable = true  
        };
        ESP_ERROR_CHECK(esp_pm_configure(&pm_config));
        ESP_ERROR_CHECK(esp_pm_lock_create(ESP_PM_APB_FREQ_MAX, 0, "no_flicker", &s_pwr_lock));
        ESP_ERROR_CHECK(esp_pm_lock_acquire(s_pwr_lock));
    #endif

    /************ LED Initialization ************/
    led_init();
    led_set_state(LED_NORMAL);
    
    /************ NVS Initialization ************/
    esp_err_t ret = nvs_flash_init();
    if (ret == ESP_ERR_NVS_NO_FREE_PAGES || ret == ESP_ERR_NVS_NEW_VERSION_FOUND) {
        ESP_ERROR_CHECK(nvs_flash_erase());
        ret = nvs_flash_init();
    }
    ESP_ERROR_CHECK(ret);

    wifi_init_sta();

    xTaskCreate(wifi_manager_task, "WIFI_Manager_Task", 4096, NULL, 5, &xWifiTaskHandle);

    /************ DHT20 Sensor Initialization ************/
    xTaskCreate(dht20_sensor, "DHT20_Task", 4096, NULL, 5, &xDhtTaskHandle);
    vTaskDelay(pdMS_TO_TICKS(100));

    /************ SD Card Initialization ************/
    if (spi_bus_init_card() != ESP_OK) { 
        ESP_LOGE(TAG, "Erro SD Card Configuraçao Default sera usada (5s).");
        xTaskNotify(xDhtTaskHandle, 5000, eSetValueWithOverwrite);
    }

    /************ Display Initialization ************/
    /**  Display **/
    display_init_attached();
    /**  Backlight Regulator **/
    display_hardware_init();

    /************ Display Task ************/
    xTaskCreate(display_manager_task, "Display_Task", 8192, NULL, 4, NULL);

    /************ Logging Task ************/
    xTaskCreate(sdcard_logging_task, "SD_Log_Task", 4096, NULL, 3, &xCardTaskHandle);
    vTaskDelay(pdMS_TO_TICKS(500));

    /************ WiFi ************/
    

    /************ MQTT Task ************/
    xTaskCreate(mqtt_client_task, "MQTT_Task", 8192, NULL, 4, &xMqttTaskHandle);

    ESP_LOGI(TAG, "Sistema totalmente iniciado.");
}