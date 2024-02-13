package com.shm.domain.lotto.domain

import java.util.SortedSet

/**
 * 로또 번호를 저장하는 클래스.
 *
 * @property numbers 로또 번호
 * @property size 로또 번호 크기
 */
data class Lotto(private val numbers: SortedSet<LottoNumber>) : Iterable<LottoNumber> by numbers {
    val size = numbers.size
    // TODO: 주 생성자에 bonus 번호 프로퍼티를 추가하기

    // TODO: 아래의 조건 검사는 현재 이 클래스가 6/45 포멧만을 따르기 때문인데 나중에 이 로또를 확장한 형태로 가진다면 각 로또가 자신의 프로퍼티 조건을 검사하도록 해야 함
    init {
        // 중복되지 않은 번호 개수 체크
        require(numbers.size == NUM_OF_LOTTO_NUMBERS) { "중복을 제외한 ${NUM_OF_LOTTO_NUMBERS}개의 번호가 필요합니다." }
    }

    constructor(iterableInt: Iterable<Int>) : this(iterableInt.map { LottoNumber(it) }.toSortedSet())
//    constructor(iterableLottoNumber: Iterable<LottoNumber>) : this(iterableLottoNumber.toSortedSet())

    override fun toString() = numbers.joinToString(" ")

    companion object {
        const val NUM_OF_LOTTO_NUMBERS = 6
    }
}
