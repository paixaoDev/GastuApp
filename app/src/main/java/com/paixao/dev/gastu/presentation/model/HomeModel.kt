package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.HomeEntity
import java.math.BigDecimal

data class HomeModel constructor(
    val currency: BigDecimal,
    val spendCurrency: BigDecimal,
    val earningCurrency: BigDecimal,
    val deals: List<DealModel>?
)

internal fun HomeEntity.toModel(): HomeModel {
    return HomeModel(currency.toBigDecimal(), spendCurrency.toBigDecimal(), earningCurrency.toBigDecimal(), deals?.map { it.toModel() })
}