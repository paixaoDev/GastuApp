package com.paixao.dev.gastu.presentation.state

import com.paixao.dev.gastu.presentation.model.HomeModel

data class HomeUiState(
    val homeModel: HomeModel = HomeModel(0f, 0f,0f,null)
)
