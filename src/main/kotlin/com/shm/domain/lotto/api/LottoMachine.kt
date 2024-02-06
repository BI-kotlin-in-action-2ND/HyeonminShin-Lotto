package com.shm.domain.lotto.api

import com.shm.domain.lotto.application.LottoGenerateService
import com.shm.domain.lotto.application.LottoGenerateService.getManualLotto
import com.shm.domain.lotto.application.LottoGenerateService.getRandomLotto
import com.shm.domain.lotto.application.LottoRepositoryService
import com.shm.domain.lotto.config.LottoPrice
import com.shm.domain.lotto.dao.LottoRepository
import com.shm.domain.lotto.view.inputListInt
import com.shm.domain.lotto.view.inputManualCount
import com.shm.global.utility.retryIfException

/**
 * 로또 컨트롤러
 */
object LottoMachine {
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
        val countLotto = LottoGenerateService.countMaximumLottoWithMoney(insertMoney)
        val manualCount = inputManualCount(countLotto)

        val repo = repository ?: LottoRepositoryService.getLottoRepository()

        // 수동 로또 생성
        // 잘못된 입력값을 다시 받도록 하는 책임은 뷰와 서비스 사이를 중개하는 컨트롤러에게 있음!
        var cnt = 1
        while (cnt <= manualCount) {
            val tmpLotto =
                retryIfException(3) {
                    val tmpList = inputListInt(cnt, manualCount)
                    getManualLotto(tmpList)
                }
            cnt++
            repo.save(tmpLotto)
        }
        check(repo.size() == manualCount) { "수동 로또 ${manualCount}개 생성 실패" }

        // 자동 로또 생성
        val randomCount = countLotto - manualCount
        repeat(randomCount) {
            repo.save(getRandomLotto())
        }

        check(repo.size() == countLotto) { "총 로또 ${countLotto}개 생성 실패" }

        return Pair(insertMoney - LottoPrice.PER_PRICE.value * countLotto.toUInt(), repo)
    }

    /**
     * @return one [LottoRepository] instance
     */
    fun getLottoRepository() = LottoRepositoryService.getLottoRepository()

    /**
     * @return the total amount of prize from given [LottoRepository]
     */
    fun getTotalWinningPrize(repository: LottoRepository) = LottoRepositoryService.totalWinningPrize(repository)

    fun getCurrentLottoPrice() = LottoPrice.PER_PRICE.value
}
