package com.shm.global.utility

fun <T> retryIfException(
    maxRetries: Int,
    block: () -> T,
): T {
    var retries = 0
    var lastException: Throwable? = null

    while (retries < maxRetries) {
        try {
            return block()
        } catch (e: Exception) {
            lastException = e
            retries++

            var currentException: Throwable? = e

            // 예외 체인을 따라가면서 caused by 에러를 찾음
            while (currentException != null) {
                currentException = currentException.cause ?: break
            }

            println(currentException?.message ?: e.message)
        }
    }

    throw RuntimeException("최대 재시도 횟수를 초과했습니다.", lastException)
}
