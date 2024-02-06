package com.shm.domain.lotto.exception

import com.shm.global.exception.InvalidInputValueException

class CannotMakeLottoNumberException(override val message: String) : InvalidInputValueException(message)
