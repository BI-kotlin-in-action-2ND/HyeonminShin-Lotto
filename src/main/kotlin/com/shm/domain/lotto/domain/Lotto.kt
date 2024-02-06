package com.shm.domain.lotto.domain

import com.shm.domain.lotto.domain.generator.LottoNumberGenerator

/**
 * [LottoNumber]를 저장하는 클래스.
 * 별도의 [LottoNumberGenerator]를 지정하여 해당 로직에 따라 [LottoNumber]를 생성할 수 있다.
 */
class Lotto private constructor(private val lottoNumber: LottoNumber) {
    companion object {
        fun getLotto(generator: LottoNumberGenerator) = Lotto(generator.generate())
    }

    fun getLottoNumbers() = lottoNumber.numbers

    override fun toString(): String = lottoNumber.toString()
}
