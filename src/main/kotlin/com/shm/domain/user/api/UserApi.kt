package com.shm.domain.user.api

import com.shm.domain.lotto.api.LottoMachine
import com.shm.domain.lotto.config.LottoPrice
import com.shm.domain.user.domain.User
import com.shm.domain.user.view.inputMoney
import com.shm.global.utility.retryIfException

object UserApi {
    private val price = LottoMachine.getCurrentLottoPrice()

    fun createUser() =
        User(
            retryIfException(3) {
                requireNotNull(inputMoney().takeIf { it >= price }) { "${price}${LottoPrice.UNIT} 이상의 돈을 넣어주세요." }
            },
        )

    fun removeUser() {
        TODO("나중에 유저 리스트 저장해야할 때 필요한 api")
    }
}
