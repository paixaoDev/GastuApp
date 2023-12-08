package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.DealEntity
import java.math.BigDecimal

data class DealModel constructor(
    val id: String,
    val userId: String,
    val date: String,
    val value: BigDecimal,
    val hasExecuted: Boolean,
    val hasFixed: Boolean,
    val name: String?,
    val description: String?,
    val category: String?,
    val dealType: String
)




internal fun List<DealEntity>.toModel(): List<DealModel> {
    return this.map { it.toModel() }
}

internal fun DealEntity.toModel(): DealModel {
    return DealModel(
        id = id,
        userId = userId,
        date = date,
        value = value,
        hasExecuted = hasExecuted,
        hasFixed = hasFixed,
        name = name,
        description = description,
        category = category,
        dealType = dealType
    )
}

internal fun DealModel.toEntity(): DealEntity {
    return DealEntity(
        id = id,
        userId = userId,
        date = date,
        value = value,
        hasExecuted = hasExecuted,
        hasFixed = hasFixed,
        name = name,
        description = description,
        category = category,
        dealType = dealType
    )
}
