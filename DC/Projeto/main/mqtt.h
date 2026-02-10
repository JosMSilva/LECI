#ifndef MQTT_H
#define MQTT_H

#include "esp_err.h"
#include "common.h"
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"

extern TaskHandle_t xMqttTaskHandle;

void mqtt_client_task(void *pvParameters);

#endif