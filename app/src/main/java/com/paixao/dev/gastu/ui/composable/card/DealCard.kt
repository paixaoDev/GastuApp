package com.paixao.dev.gastu.ui.composable.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.paixao.dev.gastu.domain.util.DealCategory
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.ui.composable.MinorInformativeItem
import com.paixao.dev.gastu.ui.theme.GastuTheme
import java.math.BigDecimal

@Composable
internal fun DealCard(
    deal: DealModel,
    modifier: Modifier = Modifier,
    onClick: (DealModel) -> Unit = {}
) {
    val hasEarning = deal.dealType == DealTypeEnum.Earning.name
    Box(Modifier.clickable { onClick.invoke(deal) }) {
        MinorInformativeItem(
            title = deal.name,
            description = listOf(
                deal.category.toString(),
                deal.description ?: "",
                if (deal.hasFixed) Fixed else "",
                if (deal.hasExecuted) Paid else NOT_PAID
            ),
            currencyValue = deal.value.toCurrency(),
            icon = if (deal.hasFixed) Icons.Default.Lock else null,
            textColor = if (hasEarning) Color.Green else Color.Red,
            iconColor = if (deal.hasExecuted) Color.Green else Color.Red,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MinorInformativeItemPreview() {
    GastuTheme {
        DealCard(
            DealModel(
                "dasda",
                "asdfqwe",
                date = "17/05/1993",
                value = BigDecimal.ZERO,
                hasExecuted = true,
                hasFixed = true,
                name = "Compra de kilo da banana",
                description = "Comprei uma banana para comer com leite",
                category = DealCategory.Food.name,
                dealType = DealTypeEnum.Spend.name
            )
        )
    }
}

const val Fixed = "Fixa"
const val Paid = "Pago"
const val NOT_PAID = "Não Pago"
