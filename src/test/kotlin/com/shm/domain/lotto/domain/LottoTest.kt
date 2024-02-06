package com.shm.domain.lotto.domain

import com.shm.domain.lotto.application.LottoGenerateService
import com.shm.domain.lotto.config.LottoNumberConfig
import com.shm.domain.lotto.domain.generator.LottoNumberGenerator
import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeTypeOf
import java.util.SortedSet

class LottoTest : BehaviorSpec({
    given("Generator별 로또 생성") {
        `when`("랜덤 로또 생성") {
            val randomLotto = LottoGenerateService.getRandomLotto()

            then("Lotto와 LottoNumber가 Null이 아님") {
                randomLotto.shouldNotBeNull()
                randomLotto.shouldBeTypeOf<Lotto>()
                // If success, it means LottoNumber.size & every element are fit to LottoNumberConfig condition.
                randomLotto.shouldNotBeNull().getLottoNumbers().shouldBeInstanceOf<SortedSet<Int>>()
            }

            then("LottoNumber가 계속 바뀌어야 함") {
                // 10 times for preventing them from coincidence
                repeat(10) {
                    val randomLotto2 = LottoGenerateService.getRandomLotto()
                    randomLotto shouldNotBe randomLotto2
                }
            }
        }

        `when`("수동 로또 생성") {
            val manualLotto = LottoGenerateService.getManualLotto(listOf(1, 2, 3, 4, 5, 6))

            then("Lotto와 LottoNumber가 Null이 아니어야 함") {
                manualLotto.shouldNotBeNull()
                manualLotto.shouldBeTypeOf<Lotto>()
                // If success, it means LottoNumber.size & every element are fit to LottoNumberConfig condition.
                manualLotto.shouldNotBeNull().getLottoNumbers().shouldBeInstanceOf<SortedSet<Int>>()
            }
        }

        `when`("Size가 잘못된 LottoNumber가 들어갔을 때") {
            then("require 예외 발생") {
                shouldThrowMessage("${LottoNumberConfig.NUM_OF_LOTTO_NUMBERS.value}개의 번호가 필요합니다.") {
                    Lotto.getLotto(
                        object : LottoNumberGenerator {
                            override val target: List<Int>
                                get() = listOf(1, 2, 3, 4, 5)
                        },
                    )
                }
            }
        }

        `when`("Size는 정상, 하지만 정상 범위 밖의 LottoNumber가 들어갔을 때") {
            then("require 예외 발생") {
                shouldThrowMessage("입력받은 번호 중 범위 밖의 번호가 존재합니다.") {
                    Lotto.getLotto(
                        object : LottoNumberGenerator {
                            override val target: List<Int>
                                get() = listOf(1, 2, 3, 4, 5, 125)
                        },
                    )
                }
            }
        }

        `when`("Size는 정상, 하지만 중복된 LottoNumber가 들어갔을 때") {
            then("check 예외 발생") {
                shouldThrowMessage("중복되지 않은 ${LottoNumberConfig.NUM_OF_LOTTO_NUMBERS.value}개의 번호가 필요합니다.") {
                    Lotto.getLotto(
                        object : LottoNumberGenerator {
                            override val target: List<Int>
                                get() = listOf(1, 2, 3, 4, 5, 5)
                        },
                    )
                }
            }
        }
    }
})
