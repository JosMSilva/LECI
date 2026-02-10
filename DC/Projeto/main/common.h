#ifndef COMMON_H
#define COMMON_H

#include <stdint.h>
#include "freertos/FreeRTOS.h"
#include "freertos/queue.h"
#include "freertos/event_groups.h"

#define WIFI_CONNECTED_BIT BIT0
#define WIFI_FAIL_BIT      BIT1
#define MQTT_CONNECTED_BIT BIT2

typedef struct {
    uint32_t sample_period_ms;  
    char wifi_ssid[32];         
    char wifi_pass[64];         
    char mqtt_broker_uri[128];   
    char device_uuid[64];      
    char device_name[64];      
    char serial_number[64];    
    char hw_version[16];       
    char author1[64];         
    char author2[64];         
} system_config_t;

typedef struct {
    float temperature;
    float humidity;
    int64_t timestamp;         
} sensor_data_t;

extern system_config_t g_sys_config;

extern EventGroupHandle_t s_wifi_event_group;


#endif 