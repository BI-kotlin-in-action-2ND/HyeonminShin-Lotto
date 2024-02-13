package com.shm.domain.lotto.view

import com.shm.domain.lotto.domain.Lotto
import com.shm.domain.lotto.domain.LottoNumber
import com.shm.global.exception.InvalidInputValueException

class InputManualLotto645 {
    companion object : InputProvider<Iterable<Int>> {
        /**
         * 콘솔 창에서 로또 번호를 입력 받아 정수 타입 [Iterable] 을 반환한다.
         *
         * @throws InvalidInputValueException 정수 변환 실패 시
         */
        override fun getInput(): Iterable<Int> {
            printManualLottoNumberInputGuide()
            return readlnOrNull()?.split(" ", ",")?.map { it.toInt() }
                ?: throw InvalidInputValueException()
        }

        private fun printManualLottoNumberInputGuide() {
            println("-----------------------------------------------")
            println("[수동 로또 번호 입력기] ")
            println("공백 또는 콤마로 구분된 ${Lotto.NUM_OF_LOTTO_NUMBERS}자리 로또 번호를 입력해주세요.")
            println("(입력 가능 번호 범위: ${LottoNumber.LOTTO_MIN_NUMBER} ~ ${LottoNumber.LOTTO_MAX_NUMBER})")
            print("-> ")
        }
    }
}
