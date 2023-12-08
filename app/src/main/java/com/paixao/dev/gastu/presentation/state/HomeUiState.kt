package com.paixao.dev.gastu.presentation.state

import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.UserModel
import java.math.BigDecimal

data class HomeUiState(
    val user: UserModel = UserModel("0", BigDecimal.ZERO, BigDecimal.ZERO,BigDecimal.ZERO),
    val dealList: List<DealModel>? = null
)
