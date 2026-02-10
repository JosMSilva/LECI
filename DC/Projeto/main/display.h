#ifndef DISPLAY_H
#define DISPLAy_H

#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "st7735.h" 
#include "common.h" 

#define MAX_HISTORY_POINTS 80 
#define GRAPH_WIDTH 160 
#define GRAPH_HEIGHT 80 
#define X_OFFSET 15 
#define Y_OFFSET_TOP 15 
#define Y_OFFSET_BOTTOM 15 
#define PLOT_AREA_WIDTH (GRAPH_WIDTH - X_OFFSET - 10) 
#define PLOT_AREA_HEIGHT (GRAPH_HEIGHT - Y_OFFSET_TOP - Y_OFFSET_BOTTOM)

typedef struct {
    float temperature; 
    float humidity;
} reading_t;

void display_hardware_init(void);

void display_manager_task(void *pvParameters);

void display_add_reading(float temp, float hum);

void display_force_reset(void);

#endif