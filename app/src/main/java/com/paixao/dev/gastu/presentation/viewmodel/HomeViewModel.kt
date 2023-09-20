package com.paixao.dev.gastu.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paixao.dev.gastu.domain.usecases.CreateDealUseCase
import com.paixao.dev.gastu.domain.usecases.PopulateHeaderUseCase
import com.paixao.dev.gastu.domain.usecases.UpdateDealUseCase
import com.paixao.dev.gastu.domain.util.Result
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.toModel
import com.paixao.dev.gastu.presentation.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val populateHeaderUseCase: PopulateHeaderUseCase,
    val createDealUseCase: CreateDealUseCase,
    val updateDealUseCase: UpdateDealUseCase,
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeUiState())
    val homeState: StateFlow<HomeUiState> = _homeState.asStateFlow()

    fun fetchHome() {
        populateHeaderUseCase().onEach { result ->
            when (result) {
                is Result.Loading -> {
                }

                is Result.Success -> {
                    _homeState.value = HomeUiState(result.data.toModel())
                }

                is Result.Error -> {

                }

                is Result.Fail -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun addDeal(deal: DealModel) {
        createDealUseCase(deal).onEach { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    fetchHome()
                }

                is Result.Error -> {

                }

                is Result.Fail -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateDeal (deal: DealModel){
        updateDealUseCase(deal).onEach { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    fetchHome()
                }

                is Result.Error -> {

                }

                is Result.Fail -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}