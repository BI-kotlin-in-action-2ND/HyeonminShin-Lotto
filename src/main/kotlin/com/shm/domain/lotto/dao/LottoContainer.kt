package com.shm.domain.lotto.dao

import com.shm.domain.lotto.config.LottoRank
import com.shm.domain.lotto.controller.LottoController
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

    constructor(lotto: Lotto) : this(lotto, LottoRank.getRank((LottoController.winningLotto.count { it in lotto })))

    fun getPrize() = rank.prize

    override fun toString(): String {
        return "${rank.rankString}: " +
            lotto.joinToString(" ") {
                if (it in LottoController.winningLotto) {
                    GREEN.formatString("%2d".format(it))
                } else {
                    RED.formatString("%2d".format(it))
                }
            }
    }
}
