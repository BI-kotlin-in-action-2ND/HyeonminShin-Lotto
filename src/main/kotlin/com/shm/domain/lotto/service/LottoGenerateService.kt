package com.shm.domain.lotto.service

import com.shm.domain.lotto.config.LottoConfig
import com.shm.domain.lotto.dao.LottoRepository
import com.shm.global.utility.retryIfException
import com.shm.global.view.TerminalColor

object LottoGenerateService {
    fun getRandomLotto() = LottoConfig.RANDOM_LOTTO_645_GENERATOR.generate()

    private fun getManualLotto() = LottoConfig.MANUAL_LOTTO_645_GENERATOR.generate()

    fun getRandomLottoRepeat(
        repeatCount: Int,
        repo: LottoRepository,
    ) = repeat(repeatCount) { repo.save(getRandomLotto()) }

    fun getManualLottoRepeat(
        repeatCount: Int,
        repo: LottoRepository,
    ) {
        var cnt = 1
        while (cnt <= repeatCount) {
            val manualLotto =
                retryIfException(3) {
                    printKthManualLotto(cnt, repeatCount)
                    getManualLotto()
                }
            cnt++
            repo.save(manualLotto)
        }
    }

    private fun printKthManualLotto(
        kth: Int,
        end: Int,
    ) {
        print(TerminalColor.BLUE.formatString("("))
        print(TerminalColor.YELLOW.formatString("$end"))
        print(TerminalColor.BLUE.formatString("개 중 "))
        print(TerminalColor.YELLOW.formatString("$kth"))
        print(TerminalColor.BLUE.formatString("번째) "))
    }
}
