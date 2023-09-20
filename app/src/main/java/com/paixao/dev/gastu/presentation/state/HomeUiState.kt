package com.paixao.dev.gastu.presentation.state

import com.paixao.dev.gastu.presentation.model.HomeModel
import java.math.BigDecimal

data class HomeUiState(
    val homeModel: HomeModel = HomeModel(BigDecimal.ZERO, BigDecimal.ZERO,BigDecimal.ZERO,null)
)
