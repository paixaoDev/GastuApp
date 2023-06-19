package com.paixao.dev.gastu.ui.util


import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.paixao.dev.gastu.extensions.removeCurrency
import com.paixao.dev.gastu.extensions.removeMask
import java.text.NumberFormat


fun String.mask(mask: String): String {
    var out = ""
    val originalText = this.removeMask()

    for (i in originalText.indices) {
        if (mask[i].toString() == "N") {
            out += originalText[i]
        } else {
            out += originalText[i]
            val before = out.substring(0, i)
            val after = out.substring(i)
            out = before + mask[i] + after
        }
    }

    if (out.length > mask.length) {
        out = out.substring(0, mask.length)
    }

    return out
}

fun TextFieldValue.mask(mask: String): TextFieldValue {
    var out = ""
    val originalText = this.text.removeMask()

    for (i in originalText.indices) {
        if (mask[i].toString() == "N") {
            out += originalText[i]
        } else {
            out += originalText[i]
            val before = out.substring(0, i)
            val after = out.substring(i)
            out = before + mask[i] + after
        }
    }

    if (out.length > mask.length) {
        out = out.substring(0, mask.length)
    }

    return TextFieldValue(out, selection = TextRange(out.length))
}

fun TextFieldValue.maskCurrency(): TextFieldValue {

    var out = ""
    val originalText = this.text.removeCurrency().replace("[,.]".toRegex(), "").trim()

    if (originalText.isNotEmpty()) {
        val parsed = originalText.toFloat() /100
        val formatted = NumberFormat.getCurrencyInstance().format((parsed))
        out = formatted
    }

    return TextFieldValue(out, selection = TextRange(out.length))
}

class Mask {
    companion object {
        const val DATE_MASK = "NN/NN/NNNN"
    }
}