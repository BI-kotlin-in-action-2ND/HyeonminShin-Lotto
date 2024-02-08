package com.shm.domain.lotto.domain

import com.shm.domain.lotto.config.LottoNumberConfig.Companion.MAX_LOTTO_NUMBER
import com.shm.domain.lotto.config.LottoNumberConfig.Companion.MIN_LOTTO_NUMBER
import com.shm.domain.lotto.config.LottoNumberConfig.Companion.NUM_OF_LOTTO_NUMBERS
import java.util.SortedSet

/**
 * 로또 번호를 저장하는 클래스.
 *
 * @property numbers 로또 번호
 */
data class Lotto(private val numbers: SortedSet<Int>) : SortedSet<Int> by numbers {
    // TODO: 주 생성자에 bonus 번호 프로퍼티를 추가하기

    // TODO: 아래의 조건 검사는 현재 이 클래스가 6/45 포멧만을 따르기 때문인데 나중에 이 로또를 확장한 형태로 가진다면 각 로또가 자신의 프로퍼티 조건을 검사하도록 해야 함
    init {
        // 중복되지 않은 번호 개수 체크
        require(numbers.size == NUM_OF_LOTTO_NUMBERS) { "중복을 제외한 ${NUM_OF_LOTTO_NUMBERS}개의 번호가 필요합니다." }
        // 번호마다 정상 구간 체크
        require(
            numbers.all {
                it in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
            },
        ) { "번호 중 ${MIN_LOTTO_NUMBER}~${MAX_LOTTO_NUMBER} 범위 밖의 번호가 존재합니다." }
    }

    constructor(intList: List<Int>) : this(intList.toSortedSet())

    override fun toString() = numbers.joinToString(" ")
}
