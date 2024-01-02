package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.extensions.smartTruncation
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.ui.theme.GastuTheme
import java.math.BigDecimal

@Composable
fun MinorInformativeItem(
    title: String?,
    description: List<String>,
    currencyValue: String,
    icon: ImageVector?,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    iconColor: Color = Color.Unspecified,
    iconModifier: Modifier = Modifier
) {
    Box(modifier = Modifier.wrapContentWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            // Name and description of item
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                MessageTextWithMinorDescription(
                    title = title?.smartTruncation(40) ?: "Deal",
                    description = description,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
            }

            // Value and alert of item
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                MessageWithIcon(
                    message = currencyValue,
                    icon = icon,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(end = 5.dp),
                    textColor = textColor,
                    iconColor = iconColor,
                    iconModifier = iconModifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MinorInformativeItemPreview() {
    GastuTheme {
        MinorInformativeItem(
            title = "Condomínio atrasado pra caralho",
            description = listOf("Lazer", "Contas de gastos referentes ao mes de abril", "Fixa"),
            currencyValue = BigDecimal.TEN.toCurrency(),
            icon = Icons.Default.Warning,
            iconColor = Color.Red
        )
    }
}