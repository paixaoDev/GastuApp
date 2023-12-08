package com.paixao.dev.gastu.domain.repository

import com.paixao.dev.gastu.domain.entities.UserEntity

interface UserRepository {
    suspend fun loadUser(userID: String): UserEntity?
    suspend fun createUser(user: UserEntity): UserEntity
    suspend fun updateUser(user: UserEntity): UserEntity
}