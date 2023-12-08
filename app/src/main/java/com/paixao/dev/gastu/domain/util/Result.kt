package com.paixao.dev.gastu.domain.util

sealed class Result<out A, out B> {

    data class Success<A> constructor(val data: A) : Result<A, Nothing>()
    data class Error<B> constructor(val message: B) : Result<Nothing, B>()
    data class Fail constructor(val throwable: Throwable? = null) : Result<Nothing, Nothing>()
    object Loading : Result<Nothing, Nothing>()
}