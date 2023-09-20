package com.paixao.dev.gastu.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun Number.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    numberFormat.minimumFractionDigits = 2

    return numberFormat.format(this).trim()
        .replace("\u00A0", "")
}

fun BigDecimal.divideBy(dividedBy: Int): BigDecimal = divide(BigDecimal(dividedBy))

fun String.toCurrency(dividedBy: Int): String {
    return clearString().trim().toSafeBigDecimal().divideBy(dividedBy).toCurrency()
}


fun String.toSafeBigDecimal(): BigDecimal = try {
    BigDecimal(this.trim())
} catch (e: Throwable) {
    BigDecimal("0")
}