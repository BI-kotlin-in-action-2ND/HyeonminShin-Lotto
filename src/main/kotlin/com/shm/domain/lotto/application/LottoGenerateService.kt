package com.shm.domain.lotto.application

import com.shm.domain.lotto.config.LottoPrice
import com.shm.domain.lotto.domain.Lotto
import com.shm.domain.lotto.domain.generator.ManualLottoNumberGenerator
import com.shm.domain.lotto.domain.generator.RandomLottoNumberGenerator

object LottoGenerateService {
    fun getRandomLotto() = Lotto.getLotto(RandomLottoNumberGenerator())

    fun getManualLotto(from: List<Int>) = Lotto.getLotto(ManualLottoNumberGenerator(from))

    fun countMaximumLottoWithMoney(money: UInt) =
        (money.div(LottoPrice.PER_PRICE.value)).toInt().takeIf { it > 0 }
            ?: throw IllegalStateException("너무 큰 돈이 입력되었습니다. 적은 돈으로 나눠서 넣어주세요.")
}
