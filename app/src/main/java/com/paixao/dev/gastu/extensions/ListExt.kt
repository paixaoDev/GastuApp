package com.paixao.dev.gastu.extensions

fun <T> merge(first: List<T>, second: List<T>): List<T> {
    return first.plus(second)
}