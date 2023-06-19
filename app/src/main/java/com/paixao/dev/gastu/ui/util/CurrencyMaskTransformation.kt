package com.paixao.dev.gastu.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.util.Locale

class CurrencyMaskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText = maskFilter(text)

    private fun maskFilter(text: AnnotatedString): TransformedText {

        val symbol = NumberFormat.getCurrencyInstance(Locale.getDefault()).currency?.symbol ?: ""
        var out = ""

        for (i in text.text.indices) {
            if (i == 0) out += "$symbol "
            out += text.text[i]
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return offset
                return text.text.length + symbol.length + 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= symbol.length) return offset
                return offset - symbol.length
            }
        }

        return TransformedText(AnnotatedString(out), numberOffsetTranslator)
    }
}