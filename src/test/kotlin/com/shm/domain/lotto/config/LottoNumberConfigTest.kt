package com.shm.domain.lotto.config

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.types.shouldBeTypeOf

class LottoNumberConfigTest : ShouldSpec({
    context("Every Number") {
        should("be <Int> type") {
            LottoNumberConfig.Companion.NUM_OF_LOTTO_NUMBERS.shouldBeTypeOf<Int>()
            LottoNumberConfig.MIN_LOTTO_NUMBER.shouldBeTypeOf<Int>()
            LottoNumberConfig.MAX_LOTTO_NUMBER.shouldBeTypeOf<Int>()
        }

        should("MIN must be smaller than MAX") {
            LottoNumberConfig.MIN_LOTTO_NUMBER shouldBeLessThanOrEqual LottoNumberConfig.MAX_LOTTO_NUMBER
        }
    }
})
