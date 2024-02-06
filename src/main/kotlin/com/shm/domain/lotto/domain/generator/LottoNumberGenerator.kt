package com.shm.domain.lotto.domain.generator

import com.shm.domain.lotto.utility.toLottoNumber

interface LottoNumberGenerator {
    val target: List<Int>

    fun generate() = target.toLottoNumber()
}
