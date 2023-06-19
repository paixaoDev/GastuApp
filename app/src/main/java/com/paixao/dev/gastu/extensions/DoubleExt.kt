package com.paixao.dev.gastu.extensions

import java.text.NumberFormat
import java.util.Locale


fun Double.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val currencySymbol = numberFormat.currency?.symbol ?: ""
    return "$currencySymbol ${this}"
}