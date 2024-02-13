package com.shm.domain.lotto.dao

import com.shm.domain.lotto.domain.Lotto
import java.util.PriorityQueue

/**
 * 로또와 그 로또의 등수(Rank)를 함께 저장하는 레포지토리.
 * @property repo 최소 힙(minimum heap)
 */
interface LottoRepository {
    val repo: PriorityQueue<LottoContainer>

    fun save(lotto: Lotto)

    fun clear()

    fun size(): Int

    fun printAll()
}
