package com.shm.domain.user.domain

import com.shm.domain.lotto.api.LottoMachine
import com.shm.domain.lotto.config.LottoPrice
import com.shm.domain.lotto.dao.LottoRepository
import com.shm.domain.user.view.printUserLotto
import com.shm.domain.user.view.realizeHowMeaninglessTheLotteryIs

class User(
    private var money: UInt,
    private val myLotto: LottoRepository = LottoMachine.getLottoRepository(),
) {
    fun setMoney(money: UInt) {
        this.money = money
    }

    fun addMoney(money: UInt) {
        this.money += money
    }

    fun buyLotto() {
        val (leftMoney, _) = LottoMachine.buyLotto(money, myLotto)
        money = leftMoney
    }

    fun showAllLotto() {
        printUserLotto()
        myLotto.printAll()

        val prize = LottoMachine.getTotalWinningPrize(myLotto)
        println()
        println("최종 획득 상금: $prize ${LottoPrice.UNIT}")
        // just for fun
        realizeHowMeaninglessTheLotteryIs(prize)
    }
}
