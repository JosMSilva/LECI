#include "led_controller.h"
#include "esp_log.h"
#include "led_strip.h"
#include "driver/gpio.h"

/************************ Led Config ************************/
#define STATUS_LED_GPIO 8
static const char *TAG = "status_led";
static led_strip_handle_t s_led_strip;
static uint8_t s_current_state = 0;

/************************ Led Initialization ************************/
void led_init(void){

    led_strip_config_t strip_config = {
        .strip_gpio_num = STATUS_LED_GPIO,
        .max_leds = 1,
    };

    led_strip_rmt_config_t rmt_config = {
        .resolution_hz = 10000000 
    };

    ESP_ERROR_CHECK(led_strip_new_rmt_device(&strip_config , &rmt_config , &s_led_strip));
    led_set_state(LED_OFF);
}

/************************ Change Led State ************************/
void led_set_state(status_led_state_t new_state){
    if(!s_led_strip){
        ESP_LOGE(TAG , "LED não iniciado");
        return;
    }

    switch(new_state){

        case LED_NORMAL:
            led_strip_set_pixel(s_led_strip , 0 , 0 , 150 , 0);
            break;

        case LED_ERROR:
            led_strip_set_pixel(s_led_strip , 0 , 150 , 0 , 0);
            break;

        case LED_TRANSMITTING:
            led_strip_set_pixel(s_led_strip , 0 , 150 , 150 , 0);
            break;
            
        case LED_OFF:
            led_strip_set_pixel(s_led_strip , 0 , 0 , 0 , 0);
            break;
    }

    led_strip_refresh(s_led_strip);
}

/************************ Add to Error List ************************/
void led_set_error(error_source_t source, bool active){
    if (active) {
        s_current_state |= source;
        led_set_state(LED_ERROR);
    } else {
        s_current_state &= ~source;
        if (s_current_state == 0) {
            led_set_state(LED_NORMAL);
        }
    }
}

/************************ Return to Last State ************************/
void led_restore_state(void) {
    if (s_current_state > 0) {
        led_set_state(LED_ERROR);
    } else {
        led_set_state(LED_NORMAL);
    }
    led_strip_refresh(s_led_strip);
}