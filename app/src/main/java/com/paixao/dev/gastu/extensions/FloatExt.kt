package com.paixao.dev.gastu.extensions

import java.text.NumberFormat
import java.util.Locale

fun Float.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return numberFormat.format((this))
}

fun String.toFloatCurrency(): Float {
    if (this.isNotEmpty()) {
        return this.removeCurrency().replace(",".toRegex(), "").trim().toFloat()
    }
    return 0f
}