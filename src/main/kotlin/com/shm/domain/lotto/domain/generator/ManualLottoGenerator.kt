package com.shm.domain.lotto.domain.generator

import com.shm.domain.lotto.domain.Lotto
import com.shm.domain.lotto.view.InputProvider

/**
 * 사용자로부터 번호를 입력받아 [Lotto] 객체를 반환함.
 *
 * 단, 사용자 입력을 [List]로 변환에 성공하는 것까지의 책임만 가짐.
 * 한마디로 내부 입력 값의 validation 책임은 [Lotto]가 가짐.
 * 그렇지 않으면 6/45 규격을 따르는 모든 로또 생성기가 결과물에 대한 같은 validation 로직을 가지게 될 것이기 때문.
 */
class ManualLottoGenerator(private val inputProvider: InputProvider<Iterable<Int>>) : LottoGenerator {
    override fun generate() = Lotto(inputProvider.getInput())
}
