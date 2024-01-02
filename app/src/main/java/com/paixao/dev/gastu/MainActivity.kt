package com.paixao.dev.gastu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.viewmodel.HomeViewModel
import com.paixao.dev.gastu.ui.composable.SimpleCurrencyForm
import com.paixao.dev.gastu.ui.composable.HomeHeaderWitchButtons
import com.paixao.dev.gastu.ui.composable.deal.DealsContentList
import com.paixao.dev.gastu.ui.composable.layout.BottomSheetLayoutContent
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.GrayBackground10
import com.paixao.dev.gastu.ui.theme.GreenBackground
import com.paixao.dev.gastu.ui.theme.RedBackground
import com.paixao.dev.gastu.ui.theme.WhiteBackground20
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchHome()
        setContent {
            GastuTheme {
                val homeUiState = viewModel.homeState.collectAsState()
                var isEditing by remember { mutableStateOf(false) }
                var editingType by remember { mutableStateOf(DealTypeEnum.Spend) }
                var showDetails by remember { mutableStateOf(true) }
                var deal: DealModel? = null

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GrayBackground10
                ) {
                    Column() {
                        HomeHeaderWitchButtons(
                            currency = homeUiState.value.user.currency.toCurrency(),
                            spendCurrency = homeUiState.value.user.spendCurrency.toCurrency(),
                            earningCurrency = homeUiState.value.user.earningCurrency.toCurrency(),
                            backgroundColor = if (homeUiState.value.user.currency > BigDecimal.ZERO) GreenBackground else RedBackground,
                            showDetails = showDetails,
                            lockOnClick = {
                                showDetails = !showDetails
                            },
                            earningsOnClick = {
                                isEditing = true
                                editingType = DealTypeEnum.Earning
                            },
                            spendOnClick = {
                                isEditing = true
                                editingType = DealTypeEnum.Spend
                            }
                        )

                        DealsContentList(
                            homeUiState.value.dealList,
                            dealClick = {
                                deal = it
                                isEditing = true
                                editingType = DealTypeEnum.valueOf(it.dealType)
                            }
                        )
                    }


                    if (isEditing) {
                        BottomSheetLayoutContent(
                            tittle = "Edite sua Transação",
                            bottomSheetContent = {
                                SimpleCurrencyForm(editingType, deal) {
                                    if (deal != null) {
                                        isEditing = false
                                        viewModel.updateDeal(it)
                                        deal = null
                                    } else {
                                        isEditing = false
                                        viewModel.addDeal(it)
                                    }
                                }
                            }
                        ) {
                            isEditing = !isEditing
                            deal = null
                        }
                    }
                }
            }
        }
    }
}
