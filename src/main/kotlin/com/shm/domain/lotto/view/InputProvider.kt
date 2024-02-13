package com.shm.domain.lotto.view

interface InputProvider<out R> {
    fun getInput(): R
}
