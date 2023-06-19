package com.paixao.dev.gastu.data.repository

import com.paixao.dev.gastu.data.local.UserDealsDao
import com.paixao.dev.gastu.domain.entities.UserDealEntity
import com.paixao.dev.gastu.domain.entities.UserEntity
import com.paixao.dev.gastu.domain.repository.UserRepository
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val dao: UserDealsDao
) : UserRepository {

    override suspend fun loadUser(userID: Int): UserDealEntity {
        val earning = dao.searchDeals(DealTypeEnum.Earning.name)
        val spends = dao.searchDeals(DealTypeEnum.Spend.name)
        return UserDealEntity(id = userID, spends = spends, earnings = earning)
    }

    override suspend fun createUser(user: UserDealEntity) {
        return dao.insertUser(UserEntity(user.id))
    }
}