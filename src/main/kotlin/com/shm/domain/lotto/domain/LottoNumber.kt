package com.shm.domain.lotto.domain

import com.shm.global.view.TerminalColor
import java.util.SortedSet

/**
 * 일련의 로또 번호를 저장하는 클래스.
 *
 * ### [numbers] 하나만 담는데도 따로 클래스를 만들어 놓은 이유는...
 * TODO: bonus 번호 프로퍼티를 추가하기
 *
 * @property [numbers] [SortedSet]
 */
@JvmInline
value class LottoNumber(val numbers: SortedSet<Int>) {
    override fun toString(): String {
        val intersect = numbers.intersect(WinningLotto.lotto.getLottoNumbers())
        return numbers.joinToString(" ") {
            if (it in intersect) {
                TerminalColor.GREEN.formatString("%2d").format(it)
            } else {
                TerminalColor.RED.formatString("%2d").format(it)
            }
        }
    }
}
