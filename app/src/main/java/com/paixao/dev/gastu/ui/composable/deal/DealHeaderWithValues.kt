package com.paixao.dev.gastu.ui.composable.deal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.paixao.dev.gastu.extensions.combineStringWithDot
import com.paixao.dev.gastu.extensions.getDayName
import com.paixao.dev.gastu.extensions.getMouthName
import com.paixao.dev.gastu.presentation.model.UserModel
import com.paixao.dev.gastu.presentation.model.mock.Deals
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.Typography
import com.paixao.dev.gastu.ui.theme.WhiteBackground20
import java.math.BigDecimal

@Composable
internal fun DealStickyHeader(dealDate: String) {
    Text(
        text = "${
            dealDate.getMouthName().uppercase()
        } - ${
            Pair(
                dealDate.getDayName().replaceFirstChar {
                    it.uppercase()
                },
                dealDate.substring(0, 2)
            ).combineStringWithDot()
        }",
        style = Typography.titleMedium
    )
}

@Preview(showBackground = true)
@Composable
fun DealHeaderWithValuesPreview() {
    GastuTheme {
        DealStickyHeader("10/06/2024")
    }
}