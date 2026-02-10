#ifndef WIFI_MANAGER_H
#define WIFI_MANAGER_H

#include "freertos/FreeRTOS.h"
#include "freertos/event_groups.h"

extern TaskHandle_t xWifiTaskHandle;

void wifi_init_sta(void);

void wifi_manager_task(void *pvParameters);

#endif