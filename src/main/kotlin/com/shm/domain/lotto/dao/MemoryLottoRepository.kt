package com.shm.domain.lotto.dao

import com.shm.domain.lotto.domain.Lotto
import java.util.PriorityQueue

class MemoryLottoRepository : LottoRepository {
    override val repo = PriorityQueue<LottoContainer>()

    override fun save(lotto: Lotto) {
        repo.add(LottoContainer(lotto))
    }

    override fun clear() = repo.clear()

    override fun printAll() = repo.forEach { println(it) }
}
