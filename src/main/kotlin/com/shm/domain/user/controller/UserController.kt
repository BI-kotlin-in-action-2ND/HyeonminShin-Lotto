package com.shm.domain.user.controller

import com.shm.domain.lotto.controller.LottoController
import com.shm.domain.user.domain.User
import com.shm.global.utility.retryIfException

object UserController {
    private val lottoPrice = LottoController.getCurrentLottoPrice()
    private val price = lottoPrice.first
    private val unit = lottoPrice.second

    fun createUser() =
        User(
            retryIfException(3) {
                inputMoney()
            },
        )

    fun removeUser() {
        TODO("나중에 유저 리스트 저장해야할 때 필요한 함수")
    }

    fun showUserBoughtLotto(user: User) {
        println(DIVIDER)
        println("[이번 주 로또 당첨 번호]")
        println(LottoController.winningLotto)
        println()
        println("[User가 구매한 Lotto 리스트] (1등부터 순차적 출력)")

        user.showAllLotto()
    }

    private const val DIVIDER = "------------------------------------------------------"

    /**
     * 사용자로부터 돈을 입력받는다.
     */
    private fun inputMoney(): UInt {
        printUserMoneyInput()
        return checkNotNull(
            checkNotNull(readlnOrNull()?.toUIntOrNull()) {
                "잘못된 정수를 입력하셨습니다. 다시 입력해주세요."
            }.takeIf { it >= price },
        ) { "$price $unit 이상의 돈을 넣어주세요." }
    }

    private fun printUserMoneyInput() {
        println()
        println("가지고 계신 돈을 투입하여 주세요. 자동으로 최대 개수의 로또를 구매합니다. (로또 한 장당 가격: $price $unit)")
        print("-> ")
    }
}
