package com.paixao.dev.gastu.domain.entities

import androidx.room.Entity

@Entity
data class DealInfoEntity constructor(
    val date: String,
    val value: Float,
    val hasExecuted: Boolean,
    val hasFixed: Boolean
)
