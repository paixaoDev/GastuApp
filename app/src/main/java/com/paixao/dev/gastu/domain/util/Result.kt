package com.paixao.dev.gastu.domain.util

sealed class Result<T>(
    val data: T? = null,
    val message: T? = null,
    val throwable: Throwable? = null
) {

    class Success<T>(data: T) : Result<T>(data = data)
    class Error<T>(message: T, data: T? = null) : Result<T>(data, message)
    class Loading<T>(data: T? = null) : Result<T>(data)
}