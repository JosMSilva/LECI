#ifndef DHT20_SENSOR_H_
#define DHT20_SENSOR_H_

#include "common.h"

extern TaskHandle_t xDhtTaskHandle;

void dht20_sensor(void *arg);

sensor_data_t dht20_last_reading(void);

#endif 