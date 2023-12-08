package com.paixao.dev.gastu.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.paixao.dev.gastu.domain.util.Converters
import java.math.BigDecimal
import javax.annotation.Nullable

@Entity(tableName = "deal")
@TypeConverters(Converters::class)
data class DealEntity constructor(
    @PrimaryKey
    val id: String,
    val userId : String,
    val date: String,
    val value: BigDecimal,
    val hasExecuted: Boolean,
    val hasFixed: Boolean,
    val dealType: String,
    @Nullable val name: String?,
    @Nullable val description: String?,
    @Nullable val category: String?
)

