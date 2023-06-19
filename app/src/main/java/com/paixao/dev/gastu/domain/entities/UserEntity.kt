package com.paixao.dev.gastu.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity constructor(
    @PrimaryKey
    val id: Int
)
