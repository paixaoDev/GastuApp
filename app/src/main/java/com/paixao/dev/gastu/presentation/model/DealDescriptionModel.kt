package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.DealDescriptionEntity

data class DealDescriptionModel constructor(
    val name: String?,
    val description: String?,
    val category: String?
)

internal fun DealDescriptionEntity.toModel(): DealDescriptionModel {
    return DealDescriptionModel(name, description, category)
}

internal fun DealDescriptionModel.toEntity(): DealDescriptionEntity {
    return DealDescriptionEntity(name ?: "", description ?: "", category)
}