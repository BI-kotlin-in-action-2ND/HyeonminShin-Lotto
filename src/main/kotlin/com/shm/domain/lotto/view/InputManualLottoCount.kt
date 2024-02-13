package com.shm.domain.lotto.view

class InputManualLottoCount(private val totalCount: Int) : InputProvider<Int> {
    init {
        require(totalCount > 0) { "1 이상의 선택 가능한 수동 로또의 수가 있어야 합니다." }
    }

    private val ifFail = 0

    /**
     * 만들 수 있는 로또의 총 개수 중 몇 개를 수동으로 만들건지 입력받음.
     * @return [Int] 수동 로또의 개수
     */
    override fun getInput(): Int {
        printAskManualLottoCount()
        return readlnOrNull()?.toIntOrNull()?.takeIf { it in 0..totalCount } ?: ifFail
    }

    private fun printAskManualLottoCount() {
        println()
        println("현재 구매 가능한 최대 로또의 개수는 [$totalCount]개입니다.")
        println("이 중 몇 개를 수동으로 입력하시겠습니까? (잘못 입력시 ${ifFail}개로 결정됩니다)")
        print("-> ")
    }
}
