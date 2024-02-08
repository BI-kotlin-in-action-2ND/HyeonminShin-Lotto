package com.shm.domain.lotto.service

import com.shm.domain.lotto.config.LottoNumberConfig
import com.shm.domain.lotto.dao.LottoRepository
import com.shm.domain.lotto.domain.generator.ManualLottoGenerator
import com.shm.domain.lotto.domain.generator.RandomLottoGenerator
import com.shm.global.utility.retryIfException
import com.shm.global.view.TerminalColor as TC

object LottoGenerateService {
    fun getRandomLotto() = RandomLottoGenerator.generate()

    private fun getManualLotto() = ManualLottoGenerator.generate()

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
                    printManualLottoNumberInputGuide(cnt, repeatCount)
                    getManualLotto()
                }
            cnt++
            repo.save(manualLotto)
        }
    }

    private fun printManualLottoNumberInputGuide(
        kth: Int,
        end: Int,
    ) {
        println("------------------------------------------------------")
        print("[수동 로또 번호 입력기] ")
        print(TC.BLUE.formatString("("))
        print(TC.YELLOW.formatString("$end"))
        print(TC.BLUE.formatString("개 중 "))
        print(TC.YELLOW.formatString("$kth"))
        println(TC.BLUE.formatString("번째)"))
        println("공백 또는 콤마로 구분된 ${LottoNumberConfig.NUM_OF_LOTTO_NUMBERS}자리 로또 번호를 입력해주세요.")
        println("(입력 가능 번호 범위: ${LottoNumberConfig.MIN_LOTTO_NUMBER} ~ ${LottoNumberConfig.MAX_LOTTO_NUMBER})")
        print("-> ")
    }
}
