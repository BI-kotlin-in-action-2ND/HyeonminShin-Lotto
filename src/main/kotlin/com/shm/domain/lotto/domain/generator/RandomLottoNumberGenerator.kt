package com.shm.domain.lotto.domain.generator

import com.shm.domain.lotto.config.LottoNumberConfig

class RandomLottoNumberGenerator : LottoNumberGenerator {
    override val target: List<Int>
        get() =
            (LottoNumberConfig.MIN_LOTTO_NUMBER.value..LottoNumberConfig.MAX_LOTTO_NUMBER.value)
                .shuffled().take(LottoNumberConfig.NUM_OF_LOTTO_NUMBERS.value)
}
