#include "hmac.h"
#include "esp_log.h"
#include "esp_efuse.h"
#include "esp_efuse_table.h" 
#include "mbedtls/md.h"
#include "mbedtls/platform.h"
#include "mbedtls/error.h"
#include <string.h>
#include "led_controller.h"

/************************ HMAC Config ************************/
static const char *TAG = "HMAC_SEC";
#define EFUSE_BLOCK_HMAC EFUSE_BLK_KEY5

/************************ Verify Key ************************/
bool is_efuse_key_written(void) {
    return !esp_efuse_block_is_empty(EFUSE_BLOCK_HMAC);
}

/************************ Create HMAC ************************/
esp_err_t generate_hmac(const uint8_t *data, size_t data_len, uint8_t *hmac_out) {
    
    mbedtls_md_context_t ctx;
    const mbedtls_md_info_t *md_info;
    int ret;
    uint8_t efuse_key[32];

    /************ Check eFuse ************/
    if (!is_efuse_key_written()) { 
        ESP_LOGE(TAG, "Chave eFuse (BLOCK%d) não está gravada/protegida. Não é possível gerar HMAC.", 5);
        led_set_error(ERR_HMAC, true);
        return ESP_FAIL;
    }

    /************ Read eFuse ************/
    esp_err_t err = esp_efuse_read_block(EFUSE_BLOCK_HMAC, efuse_key, 0, 256);
    if (err != ESP_OK) {
        ESP_LOGE(TAG, "Falha ao ler eFuse: %s", esp_err_to_name(err));
        led_set_error(ERR_HMAC, true);
        return err;
    }

    /************ Check Out Buffer ************/
    if(hmac_out == NULL){
        ESP_LOGE(TAG, "Output HMAC buffer é NULL.");
        led_set_error(ERR_HMAC, true);
        return ESP_ERR_INVALID_ARG;
    }

    /************ mbedTLS for SHA256 Initialization ************/
    mbedtls_md_init(&ctx);
    md_info = mbedtls_md_info_from_type(MBEDTLS_MD_SHA256);

    /************ SHA256 Fail ************/
    if (md_info == NULL) {
        ESP_LOGE(TAG, "SHA256 nao encontrado");
        led_set_error(ERR_HMAC, true);
        mbedtls_md_free(&ctx);
        memset(efuse_key, 0, sizeof(efuse_key));
        return ESP_FAIL;
    }

    /************ Setup Fail ************/
    if (mbedtls_md_setup(&ctx, md_info, 1) != 0) {
        ESP_LOGE(TAG, "Falha setup MD");
        led_set_error(ERR_HMAC, true);
        mbedtls_md_free(&ctx);
        memset(efuse_key, 0, sizeof(efuse_key));
        return ESP_FAIL;
    }

    /************ HMAc Initialization ************/
    ret = mbedtls_md_hmac_starts(&ctx, efuse_key, 32);
    memset(efuse_key, 0, sizeof(efuse_key));

    /** HMAC Init Error **/
    if (ret != 0) {
        led_set_error(ERR_HMAC, true);
        mbedtls_md_free(&ctx);
        return ESP_FAIL;
    }

    /************ Update with Given Data ************/
    ret = mbedtls_md_hmac_update(&ctx, data, data_len);
    if (ret != 0) {
        led_set_error(ERR_HMAC, true);
        mbedtls_md_free(&ctx);
        return ESP_FAIL;
    }

    /************ Create Hash ************/
    ret = mbedtls_md_hmac_finish(&ctx, hmac_out);
    mbedtls_md_free(&ctx);

    if (ret != 0) {
        led_set_error(ERR_HMAC, true);
        return ESP_FAIL;
    }

    led_set_error(ERR_HMAC, false);
    return ESP_OK;
}