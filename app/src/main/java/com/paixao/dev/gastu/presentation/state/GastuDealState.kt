package com.paixao.dev.gastu.presentation.state

import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.UserModel

internal sealed class GastuDealState {
    object Loading : GastuDealState()
    data class OnFetchUser(val userModel: UserModel) : GastuDealState()
    data class OnFetchUserDeals(val userDeals: List<DealModel>) : GastuDealState()
}