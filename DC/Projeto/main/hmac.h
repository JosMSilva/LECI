#ifndef HMAC_H
#define HMAC_H

#include "esp_err.h"
#include <stdbool.h>

#define HMAC_SHA256_SIZE_BYTES 32


esp_err_t generate_hmac(const uint8_t *data, size_t data_len, uint8_t *out_digest);
bool is_efuse_key_written(void);

#endif