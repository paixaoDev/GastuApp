package com.paixao.dev.gastu.domain.repository

import com.paixao.dev.gastu.domain.entities.DealEntity

interface DealRepository {
    suspend fun saveUserDeal(deal: DealEntity)
    suspend fun updateUserDeal(deal: DealEntity)
    suspend fun deleteUserDeal(deal: DealEntity)
    suspend fun loadUserDeal(dealID: String): DealEntity
    suspend fun loadUserDealList(userID: String, dealType: String): List<DealEntity>
    suspend fun loadUserDealList(userID: String): List<DealEntity>
}