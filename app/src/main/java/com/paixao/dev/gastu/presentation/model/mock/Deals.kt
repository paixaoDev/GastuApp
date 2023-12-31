package com.paixao.dev.gastu.presentation.model.mock

import com.paixao.dev.gastu.domain.util.DealCategory
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.presentation.model.DealModel
import java.math.BigDecimal

object Deals {
    internal val dealsList = listOf(
        DealModel(
            "sda1244",
            "3e1fr13241",
            date = "17/05/1993",
            value = BigDecimal.ZERO,
            hasExecuted = true,
            hasFixed = true,
            name = "Compra de kilo da banana",
            description = "Comprei uma banana para comer com leite",
            category = DealCategory.Food.name,
            DealTypeEnum.Spend.name
        ),

        DealModel(
            "123",
            "3e1fsggfr13241",
            date = "17/05/1993",
            value = BigDecimal.ZERO,
            hasExecuted = true,
            hasFixed = true,
            name = "Compra de kilo da banana",
            description = "Comprei uma banana para comer com leite",
            category = DealCategory.Food.name,
            DealTypeEnum.Spend.name
        ),

        DealModel(
            "sda12123144",
            "3e1ffdsgr13241",
            date = "17/05/1993",
            value = BigDecimal.ZERO,
            hasExecuted = true,
            hasFixed = true,
            name = "Compra de kilo da banana",
            description = "Comprei uma banana para comer com leite",
            category = DealCategory.Food.name,
            DealTypeEnum.Spend.name
        ),
    )
}