package com.paixao.dev.gastu.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun Number.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    numberFormat.minimumFractionDigits = 2

    return numberFormat.format(this).trim()
        .replace("\u00A0", " ")
}

fun BigDecimal.divideBy(dividedBy: Int): BigDecimal = divide(BigDecimal(dividedBy))

fun String.unMaskValueToBigDecimal(): BigDecimal = try {
    val regex = "[^0-9]".toRegex()
    val cleanedValue: String = regex.replace(this, "")
    BigDecimal(cleanedValue).divideBy(100)
} catch (e: Throwable) {
    BigDecimal.ZERO
}