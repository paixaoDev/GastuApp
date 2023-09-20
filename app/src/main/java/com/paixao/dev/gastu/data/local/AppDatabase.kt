package com.paixao.dev.gastu.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paixao.dev.gastu.domain.entities.DealEntity
import com.paixao.dev.gastu.domain.entities.UserEntity

@Database(entities = [UserEntity::class, DealEntity::class], version = 2)
internal abstract class AppDatabase : RoomDatabase() {
    abstract val dao: UserDealsDao
}
