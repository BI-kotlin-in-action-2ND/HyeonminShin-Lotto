package com.shm.domain.lotto.domain

import com.shm.domain.lotto.application.LottoGenerateService

/**
 * 금주의 뽑힌 로또 번호.
 *
 * TODO: 일주일마다 로또 번호가 바뀌도록 설정한다.
 */
object WinningLotto {
    val lotto = LottoGenerateService.getRandomLotto()

    fun countMatchedNumbers(other: Lotto) = lotto.getLottoNumbers().intersect(other.getLottoNumbers()).size
}
