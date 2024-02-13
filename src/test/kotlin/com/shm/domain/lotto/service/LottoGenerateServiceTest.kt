package com.shm.domain.lotto.service

import com.shm.domain.lotto.dao.LottoContainer
import com.shm.domain.lotto.domain.Lotto
import com.shm.domain.lotto.domain.LottoNumber
import com.shm.domain.lotto.domain.generator.ManualLottoGenerator
import com.shm.domain.lotto.view.InputProvider
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forExactly
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.collections.shouldHaveLowerBound
import io.kotest.matchers.collections.shouldHaveUpperBound
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class LottoGenerateServiceTest : ShouldSpec({
    // operating normally
    val customManualLottoGenerator =
        ManualLottoGenerator(
            object : InputProvider<List<Int>> {
                override fun getInput(): List<Int> =
                    (LottoNumber.RAW_LOTTO_MIN_NUMBER..LottoNumber.RAW_LOTTO_MAX_NUMBER)
                        .shuffled().take(Lotto.NUM_OF_LOTTO_NUMBERS).toList()
            },
        )

    context("Random Lotto") {
        val randomLotto = LottoGenerateService.getRandomLotto()

        should("not be a null") {
            randomLotto.shouldNotBeNull()
        }

        should("have a fixed size") {
            randomLotto.size shouldBe Lotto.NUM_OF_LOTTO_NUMBERS
        }

        should("be sorted") {
            randomLotto.shouldBeSorted()
        }

        should("keep numbers in a certain range") {
            randomLotto.shouldHaveLowerBound(LottoNumber.LOTTO_MIN_NUMBER)
            randomLotto.shouldHaveUpperBound(LottoNumber.LOTTO_MAX_NUMBER)
        }
    }

    context("Manual Lotto") {
        context("NORMAL") {
            val normalManualLotto = customManualLottoGenerator.generate()

            should("not be a null") {
                normalManualLotto.shouldNotBeNull()
            }

            should("have a fixed size") {
                normalManualLotto.size shouldBe Lotto.NUM_OF_LOTTO_NUMBERS
            }

            should("be sorted") {
                normalManualLotto.shouldBeSorted()
            }

            should("keep numbers in a certain range") {
                normalManualLotto.shouldHaveLowerBound(LottoNumber.LOTTO_MIN_NUMBER)
                normalManualLotto.shouldHaveUpperBound(LottoNumber.LOTTO_MAX_NUMBER)
            }
        }

        context("ABNORMAL") {
            should("throw size error: Given few or more") {
                shouldThrowAny {
                    Lotto(
                        (LottoNumber.RAW_LOTTO_MIN_NUMBER..LottoNumber.RAW_LOTTO_MAX_NUMBER)
                            .shuffled().take(Lotto.NUM_OF_LOTTO_NUMBERS - 1).toList(),
                    )
                }
                shouldThrowAny {
                    Lotto(
                        (LottoNumber.RAW_LOTTO_MIN_NUMBER..LottoNumber.RAW_LOTTO_MAX_NUMBER)
                            .shuffled().take(Lotto.NUM_OF_LOTTO_NUMBERS + 1).toList(),
                    )
                }
            }

            should("throw size error: Deduplication") {
                // less
                shouldThrowAny {
                    Lotto(
                        (
                            (LottoNumber.LOTTO_MIN_NUMBER..<LottoNumber.LOTTO_MAX_NUMBER).take(
                                Lotto.NUM_OF_LOTTO_NUMBERS - 2,
                            ) + LottoNumber.LOTTO_MAX_NUMBER + LottoNumber.LOTTO_MAX_NUMBER
                        ).toSortedSet(),
                    )
                }
                // more
                shouldThrowAny {
                    Lotto(
                        (
                            (LottoNumber.LOTTO_MIN_NUMBER..<LottoNumber.LOTTO_MAX_NUMBER).take(
                                Lotto.NUM_OF_LOTTO_NUMBERS,
                            ) + LottoNumber.LOTTO_MAX_NUMBER + LottoNumber.LOTTO_MAX_NUMBER
                        ).toSortedSet(),
                    )
                }
            }

            should("throw range error") {
                shouldThrowAny {
                    Lotto(
                        (
                            (LottoNumber.LOTTO_MIN_NUMBER..<LottoNumber.LOTTO_MAX_NUMBER).take(
                                Lotto.NUM_OF_LOTTO_NUMBERS - 2,
                            ) + (LottoNumber.LOTTO_MAX_NUMBER + 1)
                        ).toSortedSet(),
                    )
                }
                shouldThrowAny {
                    Lotto(
                        (
                            (LottoNumber.LOTTO_MIN_NUMBER..<LottoNumber.LOTTO_MAX_NUMBER).take(
                                Lotto.NUM_OF_LOTTO_NUMBERS - 2,
                            ) + (LottoNumber.LOTTO_MIN_NUMBER - 1)
                        ).toSortedSet(),
                    )
                }
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
})
