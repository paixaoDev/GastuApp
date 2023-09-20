package com.paixao.dev.gastu.ui.util


import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.paixao.dev.gastu.extensions.removeMask
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.extensions.toFloatCurrency


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

    if (this.text.isNotEmpty()) {
        val formatted = this.text.toFloatCurrency().toCurrency()
        out = formatted
    }

    return TextFieldValue(out, selection = TextRange(out.length))
}

class Mask {
    companion object {
        const val DATE_MASK = "NN/NN/NNNN"
    }
}