package com.paixao.dev.gastu.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deal")
data class DealEntity constructor(
    @PrimaryKey
    val id: String,
    val userId : String,
    @Embedded
    val info: DealInfoEntity,
    @Embedded
    val description: DealDescriptionEntity,
    val dealType: String
)

