package com.shm.domain.lotto.service

import com.shm.domain.lotto.dao.LottoRepository
import com.shm.domain.lotto.dao.MemoryLottoRepository

object LottoRepositoryService {
    /**
     * 현재 이용 가능한 메모리 레포지토리를 반환함.
     *
     * @see MemoryLottoRepository
     */
    fun getLottoRepository() = getMemoryLottoRepository()

    private fun getMemoryLottoRepository() = MemoryLottoRepository()

    /**
     * 주어진 [LottoRepository] 안에 저장된 로또들의 총 당첨 금액을 출력.
     *
     * @param target 당첨금 확인용 [LottoRepository]
     * @return 당첨금 총합
     */
    fun totalWinningPrize(target: LottoRepository): UInt {
        var total = 0u

        target.repo.forEach { total += it.getPrize() }
        return total
    }
}
