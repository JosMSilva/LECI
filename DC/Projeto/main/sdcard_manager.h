#ifndef SDCARD_MANAGER_H
#define SDCARD_MANAGER_H

#include "esp_err.h"
#include "common.h"

extern TaskHandle_t xCardTaskHandle;

esp_err_t sdcard_load_config(system_config_t *config);

void sdcard_logging_task(void *pvParameters);

#endif