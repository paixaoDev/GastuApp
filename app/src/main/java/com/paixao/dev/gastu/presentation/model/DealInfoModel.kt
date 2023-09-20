package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.DealInfoEntity
import java.math.BigDecimal

data class DealInfoModel constructor(
    val date: String,
    val value: BigDecimal,
    val hasExecuted: Boolean,
    val hasFixed: Boolean
)

internal fun DealInfoEntity.toModel(): DealInfoModel {
    return DealInfoModel(date, value.toBigDecimal(), hasExecuted, hasFixed)
}

internal fun DealInfoModel.toEntity(): DealInfoEntity {
    return DealInfoEntity(date, value.toString(), hasExecuted, hasFixed)
}