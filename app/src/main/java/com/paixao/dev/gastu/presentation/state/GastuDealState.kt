package com.paixao.dev.gastu.presentation.state

import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.HomeModel
import com.paixao.dev.gastu.presentation.model.UserDealsModel

internal sealed class GastuDealState {
    object Loading : GastuDealState()
    data class OnReceiveHomeModel(val homeModel: HomeModel) : GastuDealState()
    data class OnFetchUserDeals(val userDeals: UserDealsModel) : GastuDealState()
    data class OnFetchUserSpends(val userSpends: List<DealModel>) : GastuDealState()
    data class OnFetchUserEarnings(val userEarnings: List<DealModel>) : GastuDealState()
}