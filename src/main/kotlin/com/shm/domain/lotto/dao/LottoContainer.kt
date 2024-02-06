package com.shm.domain.lotto.dao

import com.shm.domain.lotto.config.LottoRank
import com.shm.domain.lotto.domain.Lotto
import com.shm.domain.lotto.domain.WinningLotto

/**
 * [Lotto]와 [LottoRank]를 담는 컨테이너 클래스.
 *
 * @see Lotto
 * @see LottoContainer
 * @see LottoMachine
 */
class LottoContainer private constructor(val lotto: Lotto, private val rank: LottoRank) : Comparable<LottoContainer> {
    override fun compareTo(other: LottoContainer): Int {
        return this.rank.ordinal - other.rank.ordinal
    }

    constructor(lotto: Lotto) : this(lotto, LottoRank.getRank(WinningLotto.countMatchedNumbers(lotto)))

    fun getPrize() = rank.prize

    override fun toString(): String = "${rank.rankString}: $lotto"
}
