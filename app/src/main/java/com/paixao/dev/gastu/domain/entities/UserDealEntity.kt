package com.paixao.dev.gastu.domain.entities

data class UserDealEntity constructor(
    val id: Int,
    val spends: List<DealEntity>?,
    val earnings: List<DealEntity>?
)