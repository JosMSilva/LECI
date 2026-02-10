#include "sdcard_manager.h"
#include "esp_log.h"
#include "esp_vfs_fat.h"
#include "sdmmc_cmd.h"
#include "cJSON.h"
#include <string.h>
#include <sys/unistd.h>
#include <sys/stat.h>
#include <dirent.h>
#include "led_controller.h"
#include "hmac.h"
#include "dht20_sensor.h"
#include "display.h"
#include "wifi_manager.h"
#include <string.h>
#include "mqtt.h"

/************************ SD Card Pin and Config ************************/
#define MOUNT_POINT "/sdcard"
#define PIN_CS_SD 18
static const char *TAG = "STORAGE";
static sdmmc_card_t *card;
static bool is_card_mounted = false;
static bool was_unmounted = false;

/************************ Communication Tasks ************************/
TaskHandle_t xCardTaskHandle = NULL;

/************************ SD Card Mounter ************************/
static esp_err_t internal_mount(void){
    if (is_card_mounted) return ESP_OK; 

    esp_vfs_fat_sdmmc_mount_config_t mount_config = {
        .format_if_mount_failed = false,
        .max_files = 5,
        .allocation_unit_size = 16 * 1024
    };

    sdmmc_host_t host = SDSPI_HOST_DEFAULT();
    host.slot = SPI2_HOST;

    sdspi_device_config_t slot_config = SDSPI_DEVICE_CONFIG_DEFAULT();
    slot_config.gpio_cs = PIN_CS_SD;
    slot_config.host_id = host.slot;

    esp_err_t ret = esp_vfs_fat_sdspi_mount(MOUNT_POINT, &host, &slot_config, &mount_config, &card);
    if (ret != ESP_OK) {
        ESP_LOGE(TAG, "Falha ao montar o cartão SD (%s)", esp_err_to_name(ret));
        led_set_error(ERR_SD, true);
        return ret;
    }
    is_card_mounted = true;
    vTaskDelay(pdMS_TO_TICKS(100));
    if(was_unmounted) display_force_reset();
    return ESP_OK;
}

/************************ SD Card Unmounter ************************/
static void internal_unmount(void){
    if (!is_card_mounted) return; 

    esp_vfs_fat_sdcard_unmount(MOUNT_POINT, card);
    is_card_mounted = false;
    was_unmounted = true;
    card = NULL;
    led_set_error(ERR_SD, true);
}

/************************ Read File from SD ************************/
static char* read_file_to_string(const char *path) {

    /** Open File **/
    FILE *f = fopen(path, "r");
    if (f == NULL) {
        ESP_LOGE(TAG, "Ficheiro não encontrado: %s", path);
        led_set_error(ERR_SD, true);
        return NULL;
    }

    /** Read File Content **/
    fseek(f, 0, SEEK_END);
    long length = ftell(f);
    fseek(f, 0, SEEK_SET);
    char *buffer = malloc(length + 1);
    if (buffer) {
        fread(buffer, 1, length, f);
        buffer[length] = '\0';
    }

    /** Close File **/
    fclose(f);
    return buffer;
}

/************************ SD Card Infor Parser ************************/
esp_err_t sdcard_load_config(system_config_t *config) {
    if (!is_card_mounted) return ESP_FAIL;

    char old_ssid[32];
    strncpy(old_ssid, config->wifi_ssid, sizeof(old_ssid));
    char old_pass[64];
    strncpy(old_pass, config->wifi_pass, sizeof(old_pass));

    /************ Builder Information ************/
    char *mfg_json_str = read_file_to_string(MOUNT_POINT "/config_mfg.json");
    if (mfg_json_str) {
        cJSON *root = cJSON_Parse(mfg_json_str);
        if (root) {

            /************ Device UUID ************/
            cJSON *uuid = cJSON_GetObjectItem(root, "device_uuid");
            if (cJSON_IsString(uuid) && uuid->valuestring) {
                strncpy(config->device_uuid, uuid->valuestring, sizeof(config->device_uuid));
            }

            /************ Serial Number ************/
            cJSON *sn = cJSON_GetObjectItem(root, "serial_number");
            if (cJSON_IsString(sn) && sn->valuestring) {
                strncpy(config->serial_number, sn->valuestring, sizeof(config->serial_number));
            }

            /************ Hardware Version ************/
            cJSON *hw = cJSON_GetObjectItem(root, "hw_version");
            if (cJSON_IsString(hw) && hw->valuestring) {
                strncpy(config->hw_version, hw->valuestring, sizeof(config->hw_version));
            }

            /************ Authors ************/
            cJSON *a1 = cJSON_GetObjectItem(root, "author1");
            if (cJSON_IsString(a1) && a1->valuestring) {
                strncpy(config->author1, a1->valuestring, sizeof(config->author1));
            }

            cJSON *a2 = cJSON_GetObjectItem(root, "author2");
            if (cJSON_IsString(a2) && a2->valuestring) {
                strncpy(config->author2, a2->valuestring, sizeof(config->author2));
            }

            cJSON_Delete(root);
        }
        free(mfg_json_str);
    } else {
        ESP_LOGE(TAG, "Ficheiro config_mfg.json nao encontrado!");
        led_set_error(ERR_SD, true);
        return ESP_FAIL;
    }

    /************ User Information ************/
    char *user_json_str = read_file_to_string(MOUNT_POINT"/config.json");

    if (user_json_str) {
        cJSON *root = cJSON_Parse(user_json_str);
        
        /************ Reading Period ************/
        cJSON *period = cJSON_GetObjectItem(root, "period_ms");
        if (cJSON_IsNumber(period)) {
            config->sample_period_ms = period->valueint;
            
            /** Notify Sensor **/
            if (xDhtTaskHandle != NULL) {
                xTaskNotify(xDhtTaskHandle, config->sample_period_ms, eSetValueWithOverwrite);
            } else {
                ESP_LOGE(TAG, "O Handle do Sensor é NULL! O SD não consegue notificar.");
            }
        }

        /************ Wifi ssid ************/
        cJSON *ssid = cJSON_GetObjectItem(root, "wifi_ssid");
        if (cJSON_IsString(ssid) && ssid->valuestring) {
            memset(config->wifi_ssid, 0, sizeof(config->wifi_ssid));
            strncpy(config->wifi_ssid, ssid->valuestring, sizeof(config->wifi_ssid));
        }

        /************ Wifi password ************/
        cJSON *pass = cJSON_GetObjectItem(root, "wifi_pass");
        if (cJSON_IsString(pass) && pass->valuestring) {
            memset(config->wifi_pass, 0, sizeof(config->wifi_pass));
            strncpy(config->wifi_pass, pass->valuestring, sizeof(config->wifi_pass));
        }

        /************ MQTT Broker URI ************/
        cJSON *uri = cJSON_GetObjectItem(root, "mqtt_broker_uri");
        if (cJSON_IsString(uri) && (uri->valuestring != NULL)) {
            memset(config->mqtt_broker_uri, 0, sizeof(config->mqtt_broker_uri));
            strncpy(config->mqtt_broker_uri, uri->valuestring, sizeof(config->mqtt_broker_uri) - 1);
        }

        cJSON_Delete(root);
        free(user_json_str);

        
        if (xWifiTaskHandle != NULL) {
            xTaskNotifyGive(xWifiTaskHandle);
        }
        

        if (xMqttTaskHandle != NULL) {
            xTaskNotifyGive(xMqttTaskHandle);
        }
    }else {
        ESP_LOGE(TAG, "Ficheiro config.json nao encontrado!");
        led_set_error(ERR_SD, true);
        return ESP_FAIL;
    }

    led_set_error(ERR_SD, false);
    return ESP_OK;
}

/************************ Hashed Logging on File ************************/
void sdcard_logging_task(void *pvParameters) {

    /************ Task Handling ************/
    xCardTaskHandle = xTaskGetCurrentTaskHandle();

    /************ Mount SD Card ************/
    if (internal_mount() == ESP_OK) {
        sdcard_load_config(&g_sys_config);
    }


    while (1) {

        /** Wait For Data **/
        ulTaskNotifyTake(pdTRUE, portMAX_DELAY); 
        sensor_data_t data = dht20_last_reading();

        /** Try to Mount if Disconnected **/
        if(!is_card_mounted){
            if(internal_mount() == ESP_OK){
                sdcard_load_config(&g_sys_config);
            } else {
                continue;
            }
        }

        /** Check eFuse for Key **/
        if (!is_efuse_key_written()) {
            ESP_LOGE(TAG, "Chave HMAC em Falta. Ingorando log auditavel.");
            continue;
        }

        /** Create Log Item From Data **/
        uint8_t hmac_digest[HMAC_SHA256_SIZE_BYTES];
        uint8_t data_arr[sizeof(int64_t) + sizeof(float) * 2];
        memcpy(data_arr, &data.timestamp, sizeof(int64_t));
        memcpy(data_arr + sizeof(int64_t), &data.temperature, sizeof(float));
        memcpy(data_arr + sizeof(int64_t) + sizeof(float), &data.humidity, sizeof(float));

        /** Create HMAC **/
        if (generate_hmac(data_arr, sizeof(data_arr), hmac_digest) != ESP_OK) {
            ESP_LOGE(TAG, "Falha ao gerar HMAC.");
            continue;;
        }

        /** HMAC to HEX String **/
        char hmac_str[HMAC_SHA256_SIZE_BYTES * 2 + 1];
        for (int i = 0; i < HMAC_SHA256_SIZE_BYTES; i++) {
            sprintf(&hmac_str[i * 2], "%02x", hmac_digest[i]);
        }

        /** Create Log Entry **/
        char log_entry[512]; 
        snprintf(log_entry, sizeof(log_entry), 
             "%" PRIi64 ",%.2f,%.2f,%s\n", 
             data.timestamp, data.temperature, data.humidity, hmac_str);

        /** Open Write File **/
        const char *log_path = MOUNT_POINT"/audit.log";
        FILE *f = fopen(log_path, "a");
        
        /** Write Log to File **/
        if (f == NULL) {
            ESP_LOGE(TAG, "Falha ao abrir log auditável para escrita: %s", log_path);
            led_set_error(ERR_SD, true);
            internal_unmount();
            continue;
        } else {
            if (fputs(log_entry, f) == EOF) {
                ESP_LOGE(TAG, "Erro de escrita no SD");
                led_set_error(ERR_SD, true);
            }else {
                led_set_error(ERR_SD, false);
            }
        }
        fclose(f);
    }
}