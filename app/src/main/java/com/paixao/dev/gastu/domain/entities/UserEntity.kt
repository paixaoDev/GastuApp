package com.paixao.dev.gastu.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.paixao.dev.gastu.domain.util.Converters
import java.math.BigDecimal

@Entity(tableName = "user")
@TypeConverters(Converters::class)
data class UserEntity constructor(
    @PrimaryKey
    val id: String,
    val currency: BigDecimal,
    val spendCurrency: BigDecimal,
    val earningCurrency: BigDecimal
)
