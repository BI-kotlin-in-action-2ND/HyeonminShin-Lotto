package com.shm.domain.lotto.config

enum class LottoPrice(val value: UInt) {
    // Lotto Price
    PER_PRICE(1_000u), ;

    companion object CurrencyUnit {
        const val UNIT = "KW" // 카카오 원
    }
}
