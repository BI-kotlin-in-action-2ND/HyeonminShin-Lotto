package com.shm.domain.lotto.config

import com.shm.domain.lotto.domain.generator.ManualLottoGenerator
import com.shm.domain.lotto.domain.generator.RandomLottoGenerator
import com.shm.domain.lotto.service.LottoGenerateService
import com.shm.domain.lotto.view.InputManualLotto645

object LottoConfig {
    val RANDOM_LOTTO_645_GENERATOR = RandomLottoGenerator()
    val MANUAL_LOTTO_645_GENERATOR = ManualLottoGenerator(InputManualLotto645)

    // TODO: 일주일마다 바뀌게 만들기
    val WINNING_LOTTO = LottoGenerateService.getRandomLotto()
}
