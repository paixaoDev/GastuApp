package com.paixao.dev.gastu.domain.entities

import androidx.room.Entity

@Entity
data class HomeEntity constructor(
    val currency: Float,
    val spendCurrency: Float,
    val earningCurrency: Float,
    val deals: List<DealEntity>?
)