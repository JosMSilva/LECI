#include "wifi_manager.h"
#include "common.h"      
#include "led_controller.h"    
#include <string.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "freertos/event_groups.h"
#include "esp_system.h"
#include "esp_wifi.h"
#include "esp_event.h"
#include "esp_log.h"
#include "nvs_flash.h"
#include "lwip/err.h"
#include "lwip/sys.h"

static const char *TAG = "WIFI_MGR";

static int s_retry_num = 0;
#define ESP_MAXIMUM_RETRY  10

TaskHandle_t xWifiTaskHandle = NULL;

/************************ WiFi Event Handler ************************/
static void event_handler(void* arg, esp_event_base_t event_base,
                                int32_t event_id, void* event_data)
{
    if (event_base == WIFI_EVENT && event_id == WIFI_EVENT_STA_DISCONNECTED) {
        if (s_retry_num < ESP_MAXIMUM_RETRY) {
            esp_wifi_connect();
            s_retry_num++;
            ESP_LOGI(TAG, "Ligação perdida/falhou. Tentativa %d/%d", s_retry_num, ESP_MAXIMUM_RETRY);
        } else {
            xEventGroupSetBits(s_wifi_event_group, WIFI_FAIL_BIT);
            ESP_LOGE(TAG, "Falha crítica na conexão Wi-Fi.");
            vTaskDelay(pdMS_TO_TICKS(5000)); 
            s_retry_num = 0;
            esp_wifi_connect(); 
        }
        led_set_error(ERR_WIFI, true); 
    } 
    else if (event_base == IP_EVENT && event_id == IP_EVENT_STA_GOT_IP) {
        ip_event_got_ip_t* event = (ip_event_got_ip_t*) event_data;
        ESP_LOGI(TAG, "IP Obtido: " IPSTR, IP2STR(&event->ip_info.ip));
        s_retry_num = 0;
        
        xEventGroupSetBits(s_wifi_event_group, WIFI_CONNECTED_BIT);
        led_set_error(ERR_WIFI, false);
    }
}

/************************ WiFi Reconnect  ************************/
static void internal_wifi_reconnect(void){

    /************ Failed to Read SSID ************/
    if (strlen(g_sys_config.wifi_ssid) == 0) {
        ESP_LOGE(TAG, "SSID vazio. A aguardar configuração...");
        return;
    }

    /************ Configure WiFi ************/
    wifi_config_t wifi_config = {
        .sta = {
            .threshold.authmode = WIFI_AUTH_WPA2_PSK,
            .pmf_cfg = { .capable = true, .required = false },
        },
    };
    strncpy((char *)wifi_config.sta.ssid, g_sys_config.wifi_ssid, sizeof(wifi_config.sta.ssid));
    strncpy((char *)wifi_config.sta.password, g_sys_config.wifi_pass, sizeof(wifi_config.sta.password));
    
    /************ Reconnect WiFi ************/
    esp_wifi_disconnect();
    esp_wifi_stop();
    ESP_ERROR_CHECK(esp_wifi_set_mode(WIFI_MODE_STA));
    ESP_ERROR_CHECK(esp_wifi_set_config(WIFI_IF_STA, &wifi_config));
    ESP_ERROR_CHECK(esp_wifi_start());
    esp_wifi_connect();
    s_retry_num = 0;
}

/************************ Wifi Manager ************************/
void wifi_manager_task(void *pvParameters){
    xWifiTaskHandle = xTaskGetCurrentTaskHandle();

    while(1){

        /************ Sleep until SD Card Update ************/
        ulTaskNotifyTake(pdTRUE, portMAX_DELAY);
        internal_wifi_reconnect();
    }
}

/************************ Configure WiFi ************************/
void wifi_init_sta(void)
{
    s_wifi_event_group = xEventGroupCreate();
    ESP_ERROR_CHECK(esp_netif_init());
    ESP_ERROR_CHECK(esp_event_loop_create_default());
    esp_netif_create_default_wifi_sta();

    wifi_init_config_t cfg = WIFI_INIT_CONFIG_DEFAULT();
    ESP_ERROR_CHECK(esp_wifi_init(&cfg));
    ESP_ERROR_CHECK(esp_wifi_set_storage(WIFI_STORAGE_RAM));
    ESP_ERROR_CHECK(esp_event_handler_instance_register(WIFI_EVENT,
                                                        ESP_EVENT_ANY_ID,
                                                        &event_handler,
                                                        NULL,
                                                        NULL));
    ESP_ERROR_CHECK(esp_event_handler_instance_register(IP_EVENT,
                                                        IP_EVENT_STA_GOT_IP,
                                                        &event_handler,
                                                        NULL,
                                                        NULL));

    ESP_ERROR_CHECK(esp_wifi_set_mode(WIFI_MODE_STA));
    ESP_ERROR_CHECK(esp_wifi_start());
    esp_wifi_set_ps(WIFI_PS_MIN_MODEM);
}