package com.paixao.dev.gastu.ui.composable.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.paixao.dev.gastu.domain.util.DealCategory
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.presentation.model.DealDescriptionModel
import com.paixao.dev.gastu.presentation.model.DealInfoModel
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.ui.composable.MinorInformativeItem
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.WhiteBackground20
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
            image = Icons.Default.AccountCircle,
            title = deal.description.name,
            description = listOf(
                deal.description.category.toString(),
                deal.description.description ?: "",
                if (deal.info.hasFixed) Fixed else "",
                if (deal.info.hasExecuted) Paid else NOT_PAID
            ),
            currencyValue = deal.info.value.toCurrency(),
            icon = if (deal.info.hasFixed) Icons.Default.Lock else null,
            textColor = if (hasEarning) Color.Green else Color.Red,
            iconColor = if (deal.info.hasExecuted) Color.Green else Color.Red,
            modifier = modifier.background(WhiteBackground20)
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
                DealInfoModel(
                    date = "17/05/1993",
                    value = BigDecimal.ZERO,
                    hasExecuted = true,
                    hasFixed = true
                ),
                DealDescriptionModel(
                    name = "Compra de kilo da banana",
                    description = "Comprei uma banana para comer com leite",
                    category = DealCategory.Food.name
                ),
                dealType = DealTypeEnum.Spend.name
            )
        )
    }
}

const val Fixed = "Fixa"
const val Paid = "Pago"
const val NOT_PAID = "Não Pago"
