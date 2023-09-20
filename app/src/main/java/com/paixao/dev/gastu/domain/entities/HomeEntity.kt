package com.paixao.dev.gastu.domain.entities

import androidx.room.Entity

@Entity
data class HomeEntity constructor(
    val currency: String,
    val spendCurrency: String,
    val earningCurrency: String,
    val deals: List<DealEntity>?
)