package com.paixao.dev.gastu.data.repository

import com.paixao.dev.gastu.data.local.UserDealsDao
import com.paixao.dev.gastu.domain.entities.UserEntity
import com.paixao.dev.gastu.domain.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val dao: UserDealsDao
) : UserRepository {

    override suspend fun loadUser(userID: String): UserEntity {
        return dao.loadUser(userID)
    }

    override suspend fun createUser(user: UserEntity): UserEntity {
        dao.insertUser(user)
        return user
    }

    override suspend fun updateUser(user: UserEntity): UserEntity {
        dao.updateUser(user)
        return user
    }
}