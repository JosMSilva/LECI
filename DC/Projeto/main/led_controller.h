#ifndef LED_CONTROLLER_H
#define LED_CONTROLLER_H
#include <stdbool.h>

typedef enum {
    LED_OFF,
    LED_NORMAL,
    LED_ERROR,
    LED_TRANSMITTING
} status_led_state_t;

typedef enum {
    ERR_NONE = 0,
    ERR_WIFI = (1 << 0),
    ERR_SD   = (1 << 1),
    ERR_MQTT = (1 << 2),
    ERR_SENSOR = (1 << 3),
    ERR_HMAC = (1 << 4),
    ERR_DISPLAY = (1 << 5)
} error_source_t;

void led_init(void);

void led_set_state(status_led_state_t new_state);

void led_set_error(error_source_t source, bool active);

void led_restore_state(void);

#endif