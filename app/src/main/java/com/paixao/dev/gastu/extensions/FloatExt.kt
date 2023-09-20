package com.paixao.dev.gastu.extensions

import java.text.NumberFormat

fun Float.toCurrency(): String {
    val formattedValue = this
    return NumberFormat.getCurrencyInstance().format((formattedValue))
}

fun String.toFloatCurrency(): Float {
    if (this.isNotEmpty()) {
        val originalText = this.removeCurrencySymbol()
            .replace(" ".toRegex(), "")
            .replace("[,.]".toRegex(), "")
            .trim()
        return originalText.toFloat() / 100
    }
    return 0f
}