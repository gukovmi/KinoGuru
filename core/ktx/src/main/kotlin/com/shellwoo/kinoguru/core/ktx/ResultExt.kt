package com.shellwoo.kinoguru.core.ktx

inline fun <T> Result<T>.mapError(transform: (Throwable) -> Throwable): Result<T> {
    val throwable = exceptionOrNull()

    return if (throwable != null) {
        Result.failure(transform(throwable))
    } else {
        this
    }
}