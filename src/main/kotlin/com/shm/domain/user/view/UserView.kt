package com.shm.domain.user.view

import com.shm.domain.lotto.config.LottoPrice
import com.shm.domain.lotto.domain.WinningLotto

const val DIVIDER = "------------------------------------------------------"

private fun printUserMoneyInput() {
    println()
    println("가지고 계신 돈을 투입하여 주세요. 자동으로 최대 개수의 로또를 구매합니다. (로또 한 장당 가격: ${LottoPrice.PER_PRICE.value})")
    print("-> ")
}

/**
 * 사용자로부터 돈을 입력받는다.
 */
fun inputMoney(): UInt {
    printUserMoneyInput()
    return checkNotNull(readlnOrNull()?.toUIntOrNull()) { "잘못된 돈을 입력하셨습니다. 다시 입력해주세요." }
}

fun printUserLotto() {
    println(DIVIDER)
    println("[이번 주 로또 당첨 번호]")
    println(WinningLotto.lotto)
    println()
    println("[User가 구매한 Lotto 리스트] (1등부터 순차적 출력)")
}

fun realizeHowMeaninglessTheLotteryIs(earned: UInt) {
    println(
        when (earned) {
            in 0u..<1000u -> "원금 회수나 하면 다행"
            in 1000u..<5000u -> "운이 좀 좋았네"
            in 5000u..<10_000u -> "로또 잘 샀다"
            else -> "실례가 안 된다면 메로나 하나만..."
        },
    )
}
