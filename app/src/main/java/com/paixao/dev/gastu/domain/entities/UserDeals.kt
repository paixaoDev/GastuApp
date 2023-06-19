package com.paixao.dev.gastu.domain.entities

import androidx.room.Embedded
import androidx.room.Relation

class UserDeals {
    @Embedded
    var user: UserEntity? = null

    @Relation(parentColumn = "id", entityColumn = "userId", entity = DealEntity::class)
    var deals: List<DealEntity>? = null
}
