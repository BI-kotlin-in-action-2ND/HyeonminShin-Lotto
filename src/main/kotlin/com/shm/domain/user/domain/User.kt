package com.shm.domain.user.domain

import com.shm.domain.lotto.controller.LottoController
import com.shm.domain.lotto.dao.LottoRepository

class User(
    private var money: UInt,
    private val myLotto: LottoRepository = LottoController.getLottoRepository(),
) {
    fun setMoney(money: UInt) {
        this.money = money
    }

    fun addMoney(money: UInt) {
        this.money += money
    }

    fun buyLotto() {
        val (leftMoney, _) = LottoController.buyLotto(money, myLotto)
        money = leftMoney
    }

    /**
     * User가 가진 로또들을 금주의 당첨 번호와 대조하여 리스트로 보여준다.
     */
    fun showAllLotto() {
        myLotto.printAll()

        val prize = LottoController.getTotalWinningPrize(myLotto)
        val (_, unit) = LottoController.getCurrentLottoPrice()

        println()
        println("최종 획득 상금: $prize $unit")
        println()
    }
}
