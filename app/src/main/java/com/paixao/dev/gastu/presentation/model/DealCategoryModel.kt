package com.paixao.dev.gastu.presentation.model

import com.paixao.dev.gastu.domain.util.DealCategory

enum class DealCategoryModel {
    House,
    Payments,
    Food,
    Animals,
    Education,
    Recreation,
    Clothing,
    Health,
    Transport
}

internal fun DealCategory.toModel() = when (this) {
    DealCategory.House -> DealCategoryModel.House
    DealCategory.Payments -> DealCategoryModel.Payments
    DealCategory.Food -> DealCategoryModel.Food
    DealCategory.Animals -> DealCategoryModel.Animals
    DealCategory.Education -> DealCategoryModel.Education
    DealCategory.Recreation -> DealCategoryModel.Recreation
    DealCategory.Clothing -> DealCategoryModel.Clothing
    DealCategory.Health -> DealCategoryModel.Health
    DealCategory.Transport -> DealCategoryModel.Transport
}