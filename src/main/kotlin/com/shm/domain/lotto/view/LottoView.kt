package com.shm.domain.lotto.view

import com.shm.domain.lotto.config.LottoNumberConfig
import com.shm.global.exception.InvalidInputValueException
import com.shm.global.view.TerminalColor as TC

const val DIVIDER = "------------------------------------------------------"

const val FAIL_TO_GET = 0

private fun printManualLottoNumberInputGuide(
    kth: Int,
    end: Int,
) {
    println(DIVIDER)
    print("[수동 로또 번호 입력기] ")
    print(TC.BLUE.formatString("("))
    print(TC.YELLOW.formatString("$end"))
    print(TC.BLUE.formatString("개 중 "))
    print(TC.YELLOW.formatString("$kth"))
    println(TC.BLUE.formatString("번째)"))
    println("공백 또는 콤마로 구분된 ${LottoNumberConfig.NUM_OF_LOTTO_NUMBERS.value}자리 로또 번호를 입력해주세요.")
    println("(입력 가능 번호 범위: ${LottoNumberConfig.MIN_LOTTO_NUMBER.value} ~ ${LottoNumberConfig.MAX_LOTTO_NUMBER.value})")
    print("-> ")
}

/**
 * 콘솔 창에서 로또 번호를 입력 받아 [List]로 변환한다.
 */
fun inputListInt(
    kth: Int,
    end: Int,
): List<Int> {
    printManualLottoNumberInputGuide(kth, end)
    return readlnOrNull()?.split(" ", ",")?.asSequence()?.map { it.toInt() }?.toList()
        ?: throw InvalidInputValueException()
}

private fun printAskManualLottoCount(countLotto: Int) {
    println()
    println("현재 구매 가능한 최대 로또의 개수는 [$countLotto]개입니다.")
    println("이 중 몇 개를 수동으로 입력하시겠습니까? (잘못 입력시 ${FAIL_TO_GET}개로 결정됩니다)")
    print("-> ")
}

/**
 * 만들 수 있는 로또의 총 개수 중 몇 개를 수동으로 만들건지 입력받음.
 * @return [Int] 수동 로또의 개수
 */
fun inputManualCount(countLotto: Int): Int {
    printAskManualLottoCount(countLotto)
    return readlnOrNull()?.toIntOrNull()?.takeIf { it in 0..countLotto } ?: FAIL_TO_GET
}
