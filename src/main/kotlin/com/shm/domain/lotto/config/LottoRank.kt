package com.shm.domain.lotto.config

import com.shm.domain.lotto.config.LottoNumberConfig.Companion.NUM_OF_LOTTO_NUMBERS

/**
 * **ordinal**: 등수를 나타내는 지표로 사용 from 0
 */
enum class LottoRank(val rankString: String, val prize: UInt) {
    FIRST("1등", 100_000u),
    SECOND("2등", 5_000u),
    THIRD("3등", 100u),
    FOURTH("4등", 5u),
    LOSE("낙첨", 0u),
    ;

    companion object Checker {
        fun getRank(countMatched: Int) = entries.getOrNull(NUM_OF_LOTTO_NUMBERS - countMatched) ?: LOSE
    }
}
