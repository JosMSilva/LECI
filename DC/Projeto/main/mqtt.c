#include "mqtt.h"
#include "esp_log.h"
#include "mqtt_client.h"
#include "freertos/event_groups.h"
#include "led_controller.h"
#include "dht20_sensor.h"
#include "freertos/queue.h"
#include "wifi_manager.h"

/************************ Definitions ************************/
#define MQTT_BUFFER_SIZE 50
static const char *TAG = "MQTT_MGR";
static esp_mqtt_client_handle_t client = NULL;
static volatile bool mqtt_is_connected = false; 
static QueueHandle_t mqtt_offline_buffer = NULL;

/************************ Task Notification Communication ************************/
TaskHandle_t xMqttTaskHandle = NULL;

/************************ Handle MQTT Events ************************/
static void mqtt_event_handler(void *handler_args, esp_event_base_t base, int32_t event_id, void *event_data) {
    
    esp_mqtt_event_handle_t event = event_data;
    switch ((esp_mqtt_event_id_t)event_id) {

        /************ MQTT Connected ************/
        case MQTT_EVENT_CONNECTED:
            mqtt_is_connected = true;
            led_set_state(LED_TRANSMITTING);
            break;

        /************ MQTT Disconected ************/
        case MQTT_EVENT_DISCONNECTED:
            ESP_LOGW(TAG, "MQTT Desconectado: Ativar Buffer.");
            mqtt_is_connected = false;
            led_set_state(LED_NORMAL); 
            break;

        /************ MQTT Error ************/
        case MQTT_EVENT_ERROR:
            ESP_LOGE(TAG, "Erro MQTT (Tipo: %d)", event->error_handle->error_type);
            break;

        default:
            break;
    }
}

/************************ MQTT Configuration ************************/
void mqtt_start(void) {

    /** Get Broker URI from File **/
    const char *uri = g_sys_config.mqtt_broker_uri;

    /** Define Client ID **/
    const char *client_id = (strlen(g_sys_config.device_uuid) > 0) ? g_sys_config.device_uuid : "ESP32_GENERIC";

    /** MQTT Broker Config **/
    const esp_mqtt_client_config_t mqtt_cfg = {
        .broker.address.uri = uri,  
        .credentials.client_id = client_id,
    };

    /** Initialize MQTT Client **/
    client = esp_mqtt_client_init(&mqtt_cfg);
    ESP_ERROR_CHECK(esp_mqtt_client_register_event(client, ESP_EVENT_ANY_ID, mqtt_event_handler, NULL));
}

/************************ Publish Readings to Broker ************************/
static void publish_reading(const sensor_data_t *data) {

    led_set_state(LED_TRANSMITTING);

    /** Info Formatting **/
    char payload_temp[16];
    char payload_hum[16];
    char topic_temp_dynamic[128];
    char topic_hum_dynamic[128];
    const char *uuid = (strlen(g_sys_config.device_uuid) > 0) ? g_sys_config.device_uuid : "esp32-c6-p6-g11";

    /** Define Topics to Publish **/
    snprintf(topic_temp_dynamic, sizeof(topic_temp_dynamic), "telemetry/%s7temp", uuid);
    snprintf(topic_hum_dynamic, sizeof(topic_hum_dynamic), "telemetry/%s7hum", uuid);
    snprintf(payload_temp, sizeof(payload_temp), "%.2f", data->temperature);
    snprintf(payload_hum, sizeof(payload_hum), "%.2f", data->humidity);

    /** Publish to MQTT Broker **/
    int msg_id_t = esp_mqtt_client_publish(client, topic_temp_dynamic, payload_temp, 0, 1, 0);
    int msg_id_h = esp_mqtt_client_publish(client, topic_hum_dynamic, payload_hum, 0, 1, 0);
    vTaskDelay(pdMS_TO_TICKS(300));

    led_restore_state();
}

/************************ MQTT Main Logic ************************/
void mqtt_client_task(void *pvParameters) {

    xMqttTaskHandle = xTaskGetCurrentTaskHandle();

    /************ Offline Buffer ************/
    mqtt_offline_buffer = xQueueCreate(MQTT_BUFFER_SIZE, sizeof(sensor_data_t));
    if (mqtt_offline_buffer == NULL) {
        ESP_LOGE(TAG, "Falha ao criar buffer offline MQTT.");
    }

    while(strlen(g_sys_config.mqtt_broker_uri) == 0) {
        ulTaskNotifyTake(pdTRUE, portMAX_DELAY);
        if (strlen(g_sys_config.mqtt_broker_uri) == 0) {
            ESP_LOGW(TAG, "Acordei mas o URI ainda está vazio.");
        }
    }

    /************ Initialize MQTT ************/
    mqtt_start();

    /************ Data Storage ************/
    sensor_data_t current_data;
    sensor_data_t buffered_data;
    
    while (1) {

        EventBits_t wifi_bits = xEventGroupWaitBits(s_wifi_event_group, 
                                                    WIFI_CONNECTED_BIT, 
                                                    pdFALSE, 
                                                    pdTRUE, 
                                                    portMAX_DELAY);

        /************ If WiFi Connected ************/
        if ((wifi_bits & WIFI_CONNECTED_BIT) != 0) {
            static bool client_started = false;
            if (!client_started) {
                esp_mqtt_client_start(client);
                client_started = true;
            }
        }
        /************ Wait for New Sensor Data ************/
        ulTaskNotifyTake(pdTRUE, pdMS_TO_TICKS(portMAX_DELAY));
        current_data = dht20_last_reading();

        /************ If MQTT Connects ************/
        if (mqtt_is_connected) {

            /** Checks for Buffered Data **/
            if(uxQueueMessagesWaiting(mqtt_offline_buffer) > 0) {
                ESP_LOGI(TAG, "Coneccao recuperada: Dados em buffer (%d itens)...", uxQueueMessagesWaiting(mqtt_offline_buffer));
                while (xQueueReceive(mqtt_offline_buffer, &buffered_data, 0) == pdPASS) {
                    publish_reading(&buffered_data);
                    vTaskDelay(pdMS_TO_TICKS(50)); 
                }
            }

            /** Publish Current Data **/
            publish_reading(&current_data);

        } else { 

            /** Store Data in Offline Buffer **/   
            if(xQueueSend(mqtt_offline_buffer, &current_data, 0) != pdPASS) {
                ESP_LOGW(TAG, "Buffer MQTT cheio: Dados perdidos!");

                /** Discard Oldest Data **/
                sensor_data_t discarded_data;
                xQueueReceive(mqtt_offline_buffer, &discarded_data, 0);
                xQueueSend(mqtt_offline_buffer, &current_data, 0);
            }
        }
    }
}