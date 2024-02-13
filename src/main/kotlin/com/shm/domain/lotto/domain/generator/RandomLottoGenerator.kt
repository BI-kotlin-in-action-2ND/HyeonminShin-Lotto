package com.shm.domain.lotto.domain.generator

import com.shm.domain.lotto.domain.Lotto
import com.shm.domain.lotto.domain.LottoNumber

class RandomLottoGenerator : LottoGenerator {
    override fun generate() =
        Lotto(
            (LottoNumber.LOTTO_MIN_NUMBER..LottoNumber.LOTTO_MAX_NUMBER)
                .shuffled().take(Lotto.NUM_OF_LOTTO_NUMBERS).toSortedSet(),
        )
}
