#include "display.h"
#include "esp_log.h"
#include <string.h>
#include <math.h>
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
#include <stdbool.h> 
#include "driver/ledc.h"
#include "esp_adc/adc_oneshot.h"
#include "dht20_sensor.h"
#include "led_controller.h"

/************************ Display and Led Pin Config ************************/
static const char *TAG = "DISP_MGR";
#define TFT_HOST    SPI2_HOST
#define PIN_CS_TFT  22
#define PIN_DC      2
#define PIN_RST     3
#define PIN_BL      15
#define PIN_MOSI    19
#define PIN_CLK     21
#define POT_ADC_CHANNEL     ADC_CHANNEL_0 
#define TFT_BACKLIGHT_PIN   15             
#define LEDC_TIMER          LEDC_TIMER_0
#define LEDC_MODE           LEDC_LOW_SPEED_MODE
#define LEDC_OUTPUT_IO      TFT_BACKLIGHT_PIN
#define LEDC_CHANNEL        LEDC_CHANNEL_0
#define LEDC_DUTY_RES       LEDC_TIMER_12_BIT 
#define LEDC_FREQUENCY      10000
#define MAX_DUTY_VALUE      4095
#define DEFAULT_DUTY        (MAX_DUTY_VALUE / 2)
static adc_oneshot_unit_handle_t adc_handle;

/************************ Task Notification Communication ************************/
TaskHandle_t xDisplayTaskHandle = NULL;

/************************ Display Scale ************************/
#define MAX_TEMP_SCALE 40.0f
#define MAX_HUM_SCALE 100.0f
static reading_t s_history[MAX_HISTORY_POINTS]; 
static int s_data_count = 0;

/************************ Hardware Initilization ************************/
void display_hardware_init(void) {

    /************ LEDC (PWM) Initilization ************/
    ledc_timer_config_t ledc_timer = {
        .speed_mode       = LEDC_MODE,
        .timer_num        = LEDC_TIMER,
        .duty_resolution  = LEDC_DUTY_RES,
        .freq_hz          = LEDC_FREQUENCY,
        .clk_cfg          = LEDC_AUTO_CLK
    };
    ESP_ERROR_CHECK(ledc_timer_config(&ledc_timer));

    /************ LEDC Channel Initilization ************/
    ledc_channel_config_t ledc_channel = {
        .speed_mode     = LEDC_MODE,
        .channel        = LEDC_CHANNEL,
        .timer_sel      = LEDC_TIMER,
        .intr_type      = LEDC_INTR_DISABLE,
        .gpio_num       = LEDC_OUTPUT_IO,
        .duty           = DEFAULT_DUTY, 
        .hpoint         = 0
    };
    ESP_ERROR_CHECK(ledc_channel_config(&ledc_channel));

    /************ ADC Initilization ************/
    adc_oneshot_unit_init_cfg_t init_config1 = {.unit_id = ADC_UNIT_1,};
    ESP_ERROR_CHECK(adc_oneshot_new_unit(&init_config1, &adc_handle));
    adc_oneshot_chan_cfg_t config = {.bitwidth = ADC_BITWIDTH_DEFAULT,.atten = ADC_ATTEN_DB_12,};
    ESP_ERROR_CHECK(adc_oneshot_config_channel(adc_handle, POT_ADC_CHANNEL, &config)); 
}

/************************ Display Reset ************************/
void display_force_reset(void) {
    st7735_config_t cfg = {
       .mosi_io_num = PIN_MOSI,
       .sclk_io_num = PIN_CLK,
       .cs_io_num = PIN_CS_TFT,
       .dc_io_num = PIN_DC,
       .rst_io_num = PIN_RST,
       .bl_io_num = -1,
       .host_id = TFT_HOST
    };

    st7735_init(&cfg);
    st7735_set_rotation(3);
    st7735_fill_screen(ST7735_BLACK);

    /** Offsets and Graph Lines **/
    int x0 = X_OFFSET;
    int y0 = Y_OFFSET_TOP;
    int y1 = Y_OFFSET_TOP + PLOT_AREA_HEIGHT;
    st7735_fill_rect(x0, y1, PLOT_AREA_WIDTH + 1, 1, ST7735_WHITE);
    st7735_fill_rect(x0, y0, 1, PLOT_AREA_HEIGHT + 1, ST7735_WHITE);
    for (int i = 0; i <= 2; i++) {
        float max_scale = false ? MAX_TEMP_SCALE : MAX_HUM_SCALE; 
        float normalized_value = fminf(fmaxf(i * 50.0f, 0.0f), max_scale);
        int y_plot = (int)((normalized_value / max_scale) * (float)PLOT_AREA_HEIGHT);
        y_plot = PLOT_AREA_HEIGHT - y_plot;
        int y = y_plot + Y_OFFSET_TOP;
        st7735_fill_rect(x0, y, PLOT_AREA_WIDTH + 1, 1, ST7735_GRAY);
    }

    /** Title **/
    st7735_draw_string(25, 5, "Humidade e Temperatura", ST7735_WHITE, ST7735_BLACK, 1);
}


/************************ Y Coordinate Calculation ************************/
static int value_to_y_coord(float value, bool is_temperature) {
    float max_scale = is_temperature ? MAX_TEMP_SCALE : MAX_HUM_SCALE; 
    float normalized_value = fminf(fmaxf(value, 0.0f), max_scale);
    int y_plot = (int)((normalized_value / max_scale) * (float)PLOT_AREA_HEIGHT);
    y_plot = PLOT_AREA_HEIGHT - y_plot;
    return y_plot + Y_OFFSET_TOP;
}

/************************ Base Axis Draw ************************/
static void draw_axes() {

    /** Black Screen **/
    st7735_fill_screen(ST7735_BLACK);

    /** Offsets and Graph Lines **/
    int x0 = X_OFFSET;
    int y0 = Y_OFFSET_TOP;
    int y1 = Y_OFFSET_TOP + PLOT_AREA_HEIGHT;
    st7735_fill_rect(x0, y1, PLOT_AREA_WIDTH + 1, 1, ST7735_WHITE);
    st7735_fill_rect(x0, y0, 1, PLOT_AREA_HEIGHT + 1, ST7735_WHITE);
    for (int i = 0; i <= 2; i++) {
        int y = value_to_y_coord(i * 50.0f, false);
        st7735_fill_rect(x0, y, PLOT_AREA_WIDTH + 1, 1, ST7735_GRAY);
    }

    /** Title **/
    st7735_draw_string(25, 5, "Humidade e Temperatura", ST7735_WHITE, ST7735_BLACK, 1); 
}

/************************ Base Labels Draw ************************/
static void draw_labels() {

    /** Humidity **/
    st7735_draw_string(2, value_to_y_coord(100.0f, false) - 5, "100", ST7735_RED, ST7735_BLACK, 1);
    st7735_draw_string(7, value_to_y_coord(50.0f, false) - 5, "50", ST7735_RED, ST7735_BLACK, 1);
    st7735_draw_string(14, value_to_y_coord(0.0f, false) - 5, "0", ST7735_RED, ST7735_BLACK, 1);
    st7735_draw_string(0, Y_OFFSET_TOP + PLOT_AREA_HEIGHT / 2 - 5, "%", ST7735_RED, ST7735_BLACK, 1);

    /** Temprature **/
    st7735_draw_string(GRAPH_WIDTH - 15, value_to_y_coord(40.0f, true) - 5, "40", ST7735_BLUE, ST7735_BLACK, 1); // Max is 40C
    st7735_draw_string(GRAPH_WIDTH - 15, value_to_y_coord(20.0f, true) - 5, "20", ST7735_BLUE, ST7735_BLACK, 1); // Middle is 20C
    st7735_draw_string(GRAPH_WIDTH - 10, value_to_y_coord(0.0f, true) - 5, "0", ST7735_BLUE, ST7735_BLACK, 1); // Min is 0C
    st7735_draw_string(GRAPH_WIDTH - 8, Y_OFFSET_TOP + PLOT_AREA_HEIGHT / 2 - 5, "C", ST7735_BLUE, ST7735_BLACK, 1);
    
}

/************************ Add New Data to Readings to Show ************************/
void display_add_reading(float temp, float hum) {

    /************ Data Not Full ************/
    if (s_data_count < MAX_HISTORY_POINTS) {
        s_history[s_data_count].temperature = temp;
        s_history[s_data_count].humidity = hum;
        s_data_count++;
    } 
    /************ Data Full ************/
    else {
        memmove(s_history, s_history + 1, sizeof(reading_t) * (MAX_HISTORY_POINTS - 1));
        s_history[MAX_HISTORY_POINTS - 1].temperature = temp;
        s_history[MAX_HISTORY_POINTS - 1].humidity = hum;
    }
}

/************************ Draw Temp and Hum ************************/
static void draw_data() {
    int max_points = s_data_count;
    for (int i = 0; i < max_points; i++) {
        int x = X_OFFSET + (int)(((float)i / MAX_HISTORY_POINTS) * PLOT_AREA_WIDTH);
        int y_temp = value_to_y_coord(s_history[i].temperature, true);
        int y_hum = value_to_y_coord(s_history[i].humidity, false);
        st7735_fill_rect(x, y_temp, 1, 1, ST7735_BLUE); 
        st7735_fill_rect(x, y_hum, 1, 1, ST7735_RED); 
    }
}

/************************ Display Manager ************************/
void display_manager_task(void *pvParameters) {

    /************ Task Control ************/
    xDisplayTaskHandle = xTaskGetCurrentTaskHandle();

    /************ Draw Base Axes ************/
    draw_axes();
    draw_labels();

    while (1) {

        /************ Brightness Controll ************/
        int adc_raw;
        if (adc_oneshot_read(adc_handle, POT_ADC_CHANNEL, &adc_raw) == ESP_OK) {
            uint32_t duty = adc_raw;
            if(duty > MAX_DUTY_VALUE) duty = MAX_DUTY_VALUE;
            ledc_set_duty(LEDC_MODE, LEDC_CHANNEL, duty);
            ledc_update_duty(LEDC_MODE, LEDC_CHANNEL);
        } else {
            led_set_error(ERR_DISPLAY, true);
            ESP_LOGW(TAG, "Erro leitura ADC para brilho");
        }

        /************ Sensor Data ************/
        if (ulTaskNotifyTake(pdTRUE, pdMS_TO_TICKS(10000)) > 0) {
            
            sensor_data_t new_sensor_data = dht20_last_reading();
            float graph_temp = new_sensor_data.temperature;
            float graph_hum = new_sensor_data.humidity;
            display_add_reading(graph_temp, graph_hum);
            st7735_fill_rect(X_OFFSET + 1, Y_OFFSET_TOP, PLOT_AREA_WIDTH, PLOT_AREA_HEIGHT, ST7735_BLACK);
            draw_labels();
            draw_data();
            led_set_error(ERR_DISPLAY, false);
            
        }
    }
}