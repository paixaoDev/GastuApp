package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.UserDealEntity

internal data class UserDealsModel constructor(
    val id: Int,
    val spends: List<DealModel>?,
    val earnings: List<DealModel>?
)

internal fun UserDealEntity.toModel(): UserDealsModel {
    return UserDealsModel(id, spends?.map { it.toModel() }, earnings?.map { it.toModel() })
}
