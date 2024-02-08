package com.shm.domain.lotto.domain.generator

import com.shm.domain.lotto.config.LottoNumberConfig.Companion.MAX_LOTTO_NUMBER
import com.shm.domain.lotto.config.LottoNumberConfig.Companion.MIN_LOTTO_NUMBER
import com.shm.domain.lotto.config.LottoNumberConfig.Companion.NUM_OF_LOTTO_NUMBERS
import com.shm.domain.lotto.domain.Lotto

object RandomLottoGenerator : LottoGenerator {
    override fun generate() =
        Lotto(
            (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER)
                .shuffled().take(NUM_OF_LOTTO_NUMBERS).toSortedSet(),
        )
}
