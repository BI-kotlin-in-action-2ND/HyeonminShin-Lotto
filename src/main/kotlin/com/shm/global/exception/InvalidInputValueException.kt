package com.shm.global.exception

open class InvalidInputValueException : BusinessException {
    constructor(message: String, errorCode: ErrorCode) : super(message, errorCode)
    constructor(message: String) : super(message, ErrorCode.INVALID_INPUT_VALUE)
    constructor() : super("Invalid input value", ErrorCode.INVALID_INPUT_VALUE)
}
