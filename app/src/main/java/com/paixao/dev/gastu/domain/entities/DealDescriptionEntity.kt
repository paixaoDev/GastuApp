package com.paixao.dev.gastu.domain.entities

import androidx.room.Entity
import javax.annotation.Nullable

@Entity
data class DealDescriptionEntity constructor(
    val name: String,
    val description: String,
    @Nullable val category: String?
)
