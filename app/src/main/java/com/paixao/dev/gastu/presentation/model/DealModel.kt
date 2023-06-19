package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.DealEntity

data class DealModel constructor(
    val id: String,
    val userId : String,
    val info: DealInfoModel,
    val description: DealDescriptionModel,
    val dealType: String
)

internal fun DealEntity.toModel () : DealModel {
    return DealModel (id, userId, info.toModel(), description.toModel(), dealType)
}

internal fun DealModel.toEntity () : DealEntity {
    return DealEntity (id, userId, info.toEntity(), description.toEntity(), dealType)
}
