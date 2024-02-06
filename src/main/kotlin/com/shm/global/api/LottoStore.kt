package com.shm.global.api

import com.shm.domain.user.api.UserApi
import com.shm.global.view.dismissPage
import com.shm.global.view.welcomePage

object LottoStore {
    fun open() {
        welcomePage()

        try {
            val user = UserApi.createUser()
            user.buyLotto()
            user.showAllLotto()
        } catch (e: Exception) {
            println(e.message)
        } finally {
            close()
        }
    }

    /**
     * Lotto 관련 예외 처리 finally 함수
     */
    private fun close() {
        dismissPage()
    }
}
