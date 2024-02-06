package com.shm.domain.lotto.application

import com.shm.domain.lotto.dao.LottoRepository
import com.shm.domain.lotto.dao.MemoryLottoRepository

object LottoRepositoryService {
    /**
     * 현재 이용 가능한 메모리 레포지토리를 반환함.
     * @see MemoryLottoRepository
     */
    fun getLottoRepository() = getMemoryLottoRepository()

    private fun getMemoryLottoRepository() = MemoryLottoRepository()

    fun totalWinningPrize(target: LottoRepository): UInt {
        var total = 0u

        target.repo.forEach { total += it.getPrize() }
        return total
    }
}
