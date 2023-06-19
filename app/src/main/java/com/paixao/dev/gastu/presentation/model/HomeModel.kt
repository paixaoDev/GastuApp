package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.HomeEntity

data class HomeModel constructor(
    val currency: Float,
    val spendCurrency: Float,
    val earningCurrency: Float,
    val deals: List<DealModel>?
)

internal fun HomeEntity.toModel(): HomeModel {
    return HomeModel(currency, spendCurrency, earningCurrency, deals?.map { it.toModel() })
}