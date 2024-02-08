package com.shm.domain.lotto.controller

import com.shm.domain.lotto.config.LottoPrice
import com.shm.domain.lotto.controller.LottoController.winningLotto
import com.shm.domain.lotto.dao.LottoRepository
import com.shm.domain.lotto.service.LottoGenerateService
import com.shm.domain.lotto.service.LottoRepositoryService

/**
 * 로또 컨트롤러
 *
 * @property winningLotto 금주의 당첨 번호
 */
object LottoController {
    private const val FAIL_TO_GET = 0

    // TODO: 일주일마다 바뀌게 만들기
    val winningLotto = LottoGenerateService.getRandomLotto()

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
        val countLotto = countMaximumLottoWithMoney(insertMoney)
        val manualCount = inputManualCount(countLotto)

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

    // WinningLotto 관련 -------------------------------------------------------

    /**
     * @return the total amount of prize from given [LottoRepository]
     */
    fun getTotalWinningPrize(repository: LottoRepository) = LottoRepositoryService.totalWinningPrize(repository)

    // view & utility 관련 ------------------------------------------------------

    /**
     * 만들 수 있는 로또의 총 개수 중 몇 개를 수동으로 만들건지 입력받음.
     * @return [Int] 수동 로또의 개수
     */
    private fun inputManualCount(countLotto: Int): Int {
        printAskManualLottoCount(countLotto)
        return readlnOrNull()?.toIntOrNull()?.takeIf { it in 0..countLotto } ?: FAIL_TO_GET
    }

    private fun printAskManualLottoCount(countLotto: Int) {
        println()
        println("현재 구매 가능한 최대 로또의 개수는 [$countLotto]개입니다.")
        println("이 중 몇 개를 수동으로 입력하시겠습니까? (잘못 입력시 ${FAIL_TO_GET}개로 결정됩니다)")
        print("-> ")
    }

    /**
     * @return 해당 돈으로 살 수 있는 로또의 최대 장 수
     */
    private fun countMaximumLottoWithMoney(money: UInt) =
        (money.div(LottoPrice.PER_PRICE)).toInt().takeIf { it > 0 }
            ?: throw IllegalStateException("너무 큰 돈이 입력되었습니다. 적은 돈으로 나눠서 넣어주세요.")
}
