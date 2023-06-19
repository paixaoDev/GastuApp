package com.paixao.dev.gastu.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.paixao.dev.gastu.domain.entities.DealEntity
import com.paixao.dev.gastu.domain.entities.UserEntity

@Dao
interface UserDealsDao {

    //Users
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(spend: UserEntity)

    @Query("SELECT * FROM user WHERE id - :id")
    suspend fun loadUser(id: String): UserEntity

    //Deal
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeal(deal: DealEntity)

    @Update
    suspend fun updateDeal(deal: DealEntity)

    @Delete
    suspend fun deleteDeal(deal: DealEntity)

    @Query("SELECT * FROM deal WHERE id  = :id")
    suspend fun loadDeal(id: String): DealEntity

    @Query("SELECT * FROM deal WHERE dealType = :dealType")
    suspend fun searchDeals(dealType: String): List<DealEntity>
}