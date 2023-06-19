package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.DealInfoEntity

data class DealInfoModel constructor(
    val date: String,
    val value: Float,
    val hasExecuted: Boolean,
    val hasFixed: Boolean
)

internal fun DealInfoEntity.toModel(): DealInfoModel {
    return DealInfoModel(date, value, hasExecuted, hasFixed)
}

internal fun DealInfoModel.toEntity(): DealInfoEntity {
    return DealInfoEntity(date, value, hasExecuted, hasFixed)
}