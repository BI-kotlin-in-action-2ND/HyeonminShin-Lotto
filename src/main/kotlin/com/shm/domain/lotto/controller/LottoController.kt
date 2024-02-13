package com.shm.domain.lotto.controller

import com.shm.domain.lotto.config.LottoPrice
import com.shm.domain.lotto.dao.LottoRepository
import com.shm.domain.lotto.service.LottoGenerateService
import com.shm.domain.lotto.service.LottoRepositoryService
import com.shm.domain.lotto.view.InputManualLottoCount

/**
 * 로또 컨트롤러
 */
object LottoController {
    /**
     * 로또 구매.
     *
     * 새로운 [LottoRepository]를 만들어 받고 싶을 때, [repository]에 null을 넘겨주면 만들어준다.
     *
     * @param insertMoney 투입한 돈
     * @param repository 넘겨받은 로또 저장소 (default: null)
     *
     * @return Pair(사고 남은 돈, 넘겨준 [repository] (null이면 안에서 생성 후 반환))
     */
    fun buyLotto(
        insertMoney: UInt,
        repository: LottoRepository? = null,
    ): Pair<UInt, LottoRepository> {
        val countLotto =
            requireNotNull(
                (insertMoney.div(LottoPrice.PER_PRICE)).toInt().takeIf { it > 0 },
            ) { "너무 큰 돈이 입력되었습니다. 적은 돈으로 나눠서 넣어주세요." }
        val manualCount = InputManualLottoCount(countLotto).getInput()

        val repo = repository ?: LottoRepositoryService.getLottoRepository()

        // 수동 로또 생성
        LottoGenerateService.getManualLottoRepeat(manualCount, repo)
        check(repo.size() == manualCount) { "수동 로또 ${manualCount}개 생성 실패" }

        // 자동 로또 생성
        LottoGenerateService.getRandomLottoRepeat(countLotto - manualCount, repo)
        check(repo.size() == countLotto) { "총 로또 ${countLotto}개 생성 실패" }

        return Pair(insertMoney - LottoPrice.PER_PRICE * countLotto.toUInt(), repo)
    }

    /**
     * @return one [LottoRepository] instance
     */
    fun getLottoRepository() = LottoRepositoryService.getLottoRepository()

    /**
     * @return [LottoPrice]의 price와 unit(화폐 단위)
     */
    fun getCurrentLottoPrice() = Pair(LottoPrice.PER_PRICE, LottoPrice.UNIT)

    /**
     * @return the total amount of prize from given [LottoRepository]
     */
    fun getTotalWinningPrize(repository: LottoRepository) = LottoRepositoryService.totalWinningPrize(repository)
}
