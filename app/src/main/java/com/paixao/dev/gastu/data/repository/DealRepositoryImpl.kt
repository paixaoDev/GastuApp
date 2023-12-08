package com.paixao.dev.gastu.data.repository

import com.paixao.dev.gastu.data.local.UserDealsDao
import com.paixao.dev.gastu.domain.entities.DealEntity
import com.paixao.dev.gastu.domain.repository.DealRepository
import javax.inject.Inject

internal class DealRepositoryImpl @Inject constructor(
    private val dao: UserDealsDao
) : DealRepository {

    override suspend fun saveUserDeal(deal: DealEntity) {
        return dao.insertDeal(deal)
    }

    override suspend fun updateUserDeal(deal: DealEntity) {
        return dao.updateDeal(deal)
    }

    override suspend fun deleteUserDeal(deal: DealEntity) {
        return dao.deleteDeal(deal)
    }

    override suspend fun loadUserDeal(dealID: String): DealEntity {
        return dao.loadDeal(dealID)
    }

    override suspend fun loadUserDealList(userID: String, dealType: String): List<DealEntity> {
        return dao.searchDeals(dealType)
    }

    override suspend fun loadUserDealList(userID: String): List<DealEntity> {
        return dao.searchDeals()
    }
}