package com.shm.domain.lotto.domain.generator

import com.shm.domain.lotto.domain.Lotto

interface LottoGenerator {
    fun generate(): Lotto
}
