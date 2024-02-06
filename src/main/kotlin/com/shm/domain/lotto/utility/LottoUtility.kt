package com.shm.domain.lotto.utility

import com.shm.domain.lotto.config.LottoNumberConfig.MAX_LOTTO_NUMBER
import com.shm.domain.lotto.config.LottoNumberConfig.MIN_LOTTO_NUMBER
import com.shm.domain.lotto.config.LottoNumberConfig.NUM_OF_LOTTO_NUMBERS
import com.shm.domain.lotto.domain.LottoNumber

/**
 * List<Int>를 조건에 따라 검사 후 LottoNumber 객체를 반환한다.
 *
 * ### 조건
 * 1. 중복되지 않은 정수 [NUM_OF_LOTTO_NUMBERS.value]개가 있어야 한다.
 * 2. 각 정수는 개구간 [[MIN_LOTTO_NUMBER.value], [MAX_LOTTO_NUMBER.value]] 범위에 포함되어야 한다.
 *
 * @return [LottoNumber]
 */
fun List<Int>.toLottoNumber(): LottoNumber {
    // 번호 개수 체크
    require(this.size == NUM_OF_LOTTO_NUMBERS.value) { "${NUM_OF_LOTTO_NUMBERS.value}개의 번호가 필요합니다." }
    // 번호마다 정상 구간 체크
    require(
        this.all {
            it in MIN_LOTTO_NUMBER.value..MAX_LOTTO_NUMBER.value
        },
    ) { "입력받은 번호 중 범위 밖의 번호가 존재합니다." }

    return LottoNumber(
        checkNotNull(
            this.toSortedSet()
                .takeIf { it.size == NUM_OF_LOTTO_NUMBERS.value },
        ) { "중복되지 않은 ${NUM_OF_LOTTO_NUMBERS.value}개의 번호가 필요합니다." },
    )
}
