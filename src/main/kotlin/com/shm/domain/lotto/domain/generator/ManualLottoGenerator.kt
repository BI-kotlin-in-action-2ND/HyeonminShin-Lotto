package com.shm.domain.lotto.domain.generator

import com.shm.domain.lotto.domain.Lotto
import com.shm.global.exception.InvalidInputValueException

/**
 * 사용자로부터 번호를 입력받아 [Lotto] 객체를 반환함.
 *
 * 단, 사용자 입력을 [List]로 변환에 성공하는 것까지의 책임만 가짐.
 * 한마디로 내부 입력 값의 validation 책임은 [Lotto]가 가짐.
 * 그렇지 않으면 6/45 규격을 따르는 모든 로또 생성기가 결과물에 대한 같은 validation 로직을 가지게 될 것이기 때문.
 */
object ManualLottoGenerator : LottoGenerator {
    override fun generate() = Lotto(inputListInt())

    /**
     * 콘솔 창에서 로또 번호를 입력 받아 [List]로 변환한다.
     *
     * @throws InvalidInputValueException 정수 변환 실패 시
     */
    private fun inputListInt(): List<Int> {
        return readlnOrNull()?.split(" ", ",")?.map { it.toInt() }?.toList()
            ?: throw InvalidInputValueException()
    }
}
