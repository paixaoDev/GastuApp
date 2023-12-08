package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.entities.UserEntity
import java.math.BigDecimal

data class UserModel constructor(
    val id: String,
    val currency: BigDecimal,
    val spendCurrency: BigDecimal,
    val earningCurrency: BigDecimal
)

internal fun UserEntity.toModel(): UserModel {
    return UserModel(id, currency, spendCurrency, earningCurrency)
}