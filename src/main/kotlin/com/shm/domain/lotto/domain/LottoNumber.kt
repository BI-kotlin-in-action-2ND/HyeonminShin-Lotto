package com.shm.domain.lotto.domain

@JvmInline
value class LottoNumber(private val number: Int) : Comparable<LottoNumber> {
    init {
        require(number in VALID_RANGE) { NUMBER_RANGE_ERROR_MESSAGE }
    }

    override fun compareTo(other: LottoNumber): Int {
        return number.compareTo(other.number)
    }

    override fun toString(): String {
        return "%2d".format(number)
    }

    operator fun rangeTo(lottoMaxNumber: LottoNumber): List<LottoNumber> {
        return (number..lottoMaxNumber.number).map { LottoNumber(it) }
    }

    operator fun rangeUntil(lottoMaxNumber: LottoNumber) = rangeTo(lottoMaxNumber - 1)

    operator fun plus(i: Int): LottoNumber {
        return LottoNumber(requireNotNull((number + i).takeIf { it in VALID_RANGE }) { NUMBER_RANGE_ERROR_MESSAGE })
    }

    operator fun minus(i: Int): LottoNumber {
        return LottoNumber(requireNotNull((number - i).takeIf { it in VALID_RANGE }) { NUMBER_RANGE_ERROR_MESSAGE })
    }

    infix fun until(lottoNumber: LottoNumber): List<LottoNumber> {
        return (number until lottoNumber.number).map { LottoNumber(it) }
    }

    companion object {
        const val RAW_LOTTO_MIN_NUMBER = 1
        const val RAW_LOTTO_MAX_NUMBER = 45

        private val VALID_RANGE: IntRange = RAW_LOTTO_MIN_NUMBER..RAW_LOTTO_MAX_NUMBER

        val LOTTO_MIN_NUMBER = LottoNumber(RAW_LOTTO_MIN_NUMBER)
        val LOTTO_MAX_NUMBER = LottoNumber(RAW_LOTTO_MAX_NUMBER)

        val NUMBER_RANGE_ERROR_MESSAGE =
            "로또 번호는 [$LOTTO_MIN_NUMBER ~ $LOTTO_MAX_NUMBER] 범위여야 합니다."
    }
}
