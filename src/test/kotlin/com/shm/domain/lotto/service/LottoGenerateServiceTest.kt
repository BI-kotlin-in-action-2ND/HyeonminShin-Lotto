package com.shm.domain.lotto.service

import com.shm.domain.lotto.config.LottoNumberConfig
import com.shm.domain.lotto.dao.LottoContainer
import com.shm.domain.lotto.domain.Lotto
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.Order
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forExactly
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.collections.shouldHaveLowerBound
import io.kotest.matchers.collections.shouldHaveUpperBound
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

@Order(0)
class LottoGenerateServiceTest : ShouldSpec({

    context("Random Lotto") {
        val randomLotto = LottoGenerateService.getRandomLotto()

        should("not be a null") {
            randomLotto.shouldNotBeNull()
        }

        should("have a fixed size") {
            randomLotto.size shouldBe LottoNumberConfig.NUM_OF_LOTTO_NUMBERS
        }

        should("be sorted") {
            randomLotto.shouldBeSorted()
        }

        should("keep numbers in a certain range") {
            randomLotto.shouldHaveLowerBound(LottoNumberConfig.MIN_LOTTO_NUMBER)
            randomLotto.shouldHaveUpperBound(LottoNumberConfig.MAX_LOTTO_NUMBER)
        }
    }

    context("Manual Lotto") {
        context("NORMAL") {
            val normalFromList = Lotto(listOf(1, 2, 3, 4, 5, 6))
            val normalFromSortedSet = Lotto(sortedSetOf(1, 2, 3, 4, 5, 6))

            should("not be a null") {
                normalFromList.shouldNotBeNull()
                normalFromSortedSet.shouldNotBeNull()
            }

            should("have a fixed size") {
                normalFromList.size shouldBe LottoNumberConfig.NUM_OF_LOTTO_NUMBERS
                normalFromSortedSet.size shouldBe LottoNumberConfig.NUM_OF_LOTTO_NUMBERS
            }

            should("be sorted") {
                normalFromList.shouldBeSorted()
                normalFromSortedSet.shouldBeSorted()
            }

            should("keep numbers in a certain range") {
                normalFromList.shouldHaveLowerBound(LottoNumberConfig.MIN_LOTTO_NUMBER)
                normalFromList.shouldHaveUpperBound(LottoNumberConfig.MAX_LOTTO_NUMBER)

                normalFromSortedSet.shouldHaveLowerBound(LottoNumberConfig.MIN_LOTTO_NUMBER)
                normalFromSortedSet.shouldHaveUpperBound(LottoNumberConfig.MAX_LOTTO_NUMBER)
            }
        }

        context("ABNORMAL") {
            should("throw size relative error: offer little or a lot") {
                shouldThrowAny { Lotto(listOf(1, 2, 3, 4, 5)) }
                shouldThrowAny { Lotto(listOf(1, 2, 3, 4, 5, 6, 7)) }

                shouldThrowAny { Lotto(sortedSetOf(1, 2, 3, 4, 5)) }
                shouldThrowAny { Lotto(sortedSetOf(1, 2, 3, 4, 5, 6, 7)) }
            }

            should("throw size relative error: delete duplicate") {
                shouldThrowAny { Lotto(listOf(1, 2, 3, 4, 5, 5)) }
                shouldThrowAny { Lotto(listOf(1, 2, 3, 3, 4, 4, 5, 5)) }

                shouldThrowAny { Lotto(sortedSetOf(1, 2, 3, 4, 5, 5)) }
                shouldThrowAny { Lotto(sortedSetOf(1, 2, 3, 3, 4, 4, 5, 5)) }
            }

            should("throw range relative error") {
                shouldThrowAny { Lotto(listOf(1, 2, 3, 4, 5, 46)) }
                shouldThrowAny { Lotto(listOf(0, 2, 3, 4, 5, 6)) }

                shouldThrowAny { Lotto(sortedSetOf(1, 2, 3, 4, 5, 46)) }
                shouldThrowAny { Lotto(sortedSetOf(0, 2, 3, 4, 5, 6)) }
            }
        }
    }

    context("Random Lotto: Repeat") {
        should("generate normally") {
            // the number of manual lotto is bigger than 1
            val repo = LottoRepositoryService.getLottoRepository()
            val normalCount = 3
            LottoGenerateService.getRandomLottoRepeat(normalCount, repo)
            repo.size() shouldBe normalCount
            repo.repo.forExactly(normalCount) {
                it.shouldBeTypeOf<LottoContainer>()
            }
        }

        should("generate normally: count is 0") {
            // the number of manual lotto is bigger than 1
            val repo = LottoRepositoryService.getLottoRepository()
            val normalCount = 0
            LottoGenerateService.getRandomLottoRepeat(normalCount, repo)
            repo.size() shouldBe normalCount
        }
    }

    // TODO: 외부 입력을 컨트롤 할 수 있는 방법이 없을까? 이것만 해결하면 테스트 커버리지가 비약적으로 상승하는데...
    // MockK도 스태틱에 final 클래스(ex. System.in)는 모킹 안돼서 하려면 ByteInputStream인가 그걸로 해야하는데 언제 다 고치지...
//    context("Manual Lotto: Repeat") {
//        val repo = LottoRepositoryService.getLottoRepository()
//
//        should("generate normally") {
//            // the number of manual lotto is bigger than 1
//            val normalCount = 3
//            LottoGenerateService.getManualLottoRepeat(normalCount, repo)
//            repo.size() shouldBe normalCount
//            repo.repo.forExactly(normalCount) {
//                it.shouldBeTypeOf<LottoContainer>()
//            }
//        }
//
//        should("generate normally 2") {
//            // the number of manual lotto is bigger than 1
//            val normalCount = 0
//            LottoGenerateService.getManualLottoRepeat(normalCount, repo)
//            repo.size() shouldBe normalCount
//            repo.repo.forExactly(normalCount) {
//                it.shouldBeTypeOf<LottoContainer>()
//            }
//        }
//    }
})
