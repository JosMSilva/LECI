idf_component_register(SRCS "main.c" "wifi_manager.c" "dht20_sensor.c" "led_controller.c" "sdcard_manager.c" "common.c" "display.c" "mqtt.c" "hmac.c"
                       PRIV_REQUIRES spi_flash
                       INCLUDE_DIRS "."
                       PRIV_REQUIRES st7735_driver driver led_strip spi_flash esp_adc json nvs_flash fatfs esp_wifi esp_event mqtt mbedtls efuse soc)
