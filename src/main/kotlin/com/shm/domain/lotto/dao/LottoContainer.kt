package com.shm.domain.lotto.dao

import com.shm.domain.lotto.config.LottoConfig
import com.shm.domain.lotto.config.LottoRank
import com.shm.domain.lotto.domain.Lotto
import com.shm.global.view.TerminalColor.GREEN
import com.shm.global.view.TerminalColor.RED

/**
 * [Lotto]와 [LottoRank]를 담는 data 클래스.
 *
 * @see Lotto
 * @see LottoContainer
 */
data class LottoContainer(val lotto: Lotto, private val rank: LottoRank) : Comparable<LottoContainer> {
    override fun compareTo(other: LottoContainer): Int {
        return this.rank.ordinal - other.rank.ordinal
    }

    constructor(lotto: Lotto) : this(lotto, LottoRank.getRank((LottoConfig.WINNING_LOTTO.count { it in lotto })))

    fun getPrize() = rank.prize

    override fun toString(): String {
        return "${rank.rankString}: " +
            lotto.joinToString(" ") {
                if (it in LottoConfig.WINNING_LOTTO) {
                    GREEN.formatString(it.toString())
                } else {
                    RED.formatString(it.toString())
                }
            }
    }
}
