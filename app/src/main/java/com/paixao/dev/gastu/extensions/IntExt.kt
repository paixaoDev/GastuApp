package com.paixao.dev.gastu.extensions

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun Int.toCurrency (): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.currency = Currency.getInstance(Locale.getDefault())
    return numberFormat.format(this)
}