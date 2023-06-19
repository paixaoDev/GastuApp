package com.paixao.dev.gastu.domain.repository

import com.paixao.dev.gastu.domain.entities.UserDealEntity

interface UserRepository {
    suspend fun loadUser(userID: Int): UserDealEntity
    suspend fun createUser(user: UserDealEntity)
}