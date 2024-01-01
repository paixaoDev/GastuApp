package com.paixao.dev.gastu.extensions

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun String.truncation(weight: Int): String {
    return if (this.length > weight + 3) this.dropLast(this.length - weight) + "..." else this
}

fun String.smartTruncation(weight: Int): String { //10 - 8 = 2
    val stringList = this.split("\\s+".toRegex())
    var finalString = ""
    var length = 0

    for (s in stringList) {
        length += s.length
        if (length < weight) {
            finalString += "$s "
        } else {
            break
        }
    }

    return finalString.dropLast(1) + if (length > weight) "..." else ""
}

fun List<String>.inLineString(): String {
    var finalString = ""
    for (s in this) {
        if (s.isNotBlank())
            finalString += s.smartTruncation(20) + " | "
    }
    return finalString.dropLast(3)
}

fun getActualDate(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
}

fun String.minusActualDate(): String {
    val dates = this.split("/")
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeZone = TimeZone.getDefault()
    calendar.set(dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
    calendar.add(Calendar.DAY_OF_YEAR, -1)
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
}

fun String.plusActualDate(): String {
    val dates = this.split("/")
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeZone = TimeZone.getDefault()
    calendar.set(dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
    calendar.add(Calendar.DAY_OF_YEAR, +1)
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
}

fun String.getDayName(): String {
    val dates = this.split("/")
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeZone = TimeZone.getDefault()
    calendar.set(dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
    val finalValue =
        calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
            ?.replace("[,.]".toRegex(), "")
            ?.getFirst("-")
    return finalValue ?: ""
}

fun String.getMouthName(): String {
    val dates = this.split("/")
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeZone = TimeZone.getDefault()
    calendar.set(dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
    return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) ?: ""
}

fun String.getFirst(delimiters: String): String {
    return this.split(delimiters.toRegex()).first()
}

fun Pair<String, String>.combineStringWithDot(): String {
    return "${this.first}, ${this.second}"
}

fun String.removeMask(): String {
    return this.replace("[^0-9 ]".toRegex(), "")
}

fun String.removeCurrencySymbol(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val currencySymbol = numberFormat.currency?.symbol ?: "R$"
    return replace(currencySymbol, "").trim()
}

fun String.clearString(): String{
    return this
        .replace(",", "")
        .replace(".", "")
        .replace(":", "")
        .replace("(", "")
        .replace(")", "")
        .replace("[", "")
        .replace("]", "")
        .replace("{", "")
        .replace("}", "")
        .replace("//", "")
        .replace("\\", "")
        .replace("/", "")
        .replace("-", "")
        .replace("_", "")
        .replace("|", "")
        .replace(" ", "")
}