package com.paixao.dev.gastu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.extensions.combineStringWithDot
import com.paixao.dev.gastu.extensions.getDayName
import com.paixao.dev.gastu.extensions.getMouthName
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.UserModel
import com.paixao.dev.gastu.presentation.model.mock.Deals
import com.paixao.dev.gastu.presentation.viewmodel.HomeViewModel
import com.paixao.dev.gastu.ui.composable.SimpleCurrencyForm
import com.paixao.dev.gastu.ui.composable.HomeHeaderWitchButtons
import com.paixao.dev.gastu.ui.composable.card.DealCard
import com.paixao.dev.gastu.ui.composable.layout.BottomSheetLayoutContent
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.GreenBackground
import com.paixao.dev.gastu.ui.theme.RedBackground
import com.paixao.dev.gastu.ui.theme.Typography
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
                var deal: DealModel? = null
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = WhiteBackground20
                ) {
                    BottomSheetLayoutContent(
                        tittle = "Edite sua Transação",
                        show = isEditing,
                        content = {
                            DealsContentList(
                                homeUiState.value.user,
                                homeUiState.value.dealList,
                                earningClick = {
                                    isEditing = true
                                    editingType = DealTypeEnum.Earning
                                },
                                spendClick = {
                                    isEditing = true
                                    editingType = DealTypeEnum.Spend
                                },
                                dealClick = {
                                    deal = it
                                    isEditing = true
                                    editingType = DealTypeEnum.valueOf(it.dealType)
                                }
                            )
                        },
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun DealsContentList(
    userModel: UserModel,
    dealsList: List<DealModel>?,
    earningClick: () -> Unit = {},
    spendClick: () -> Unit = {},
    dealClick: (DealModel) -> Unit = {}
) {
    var showDetails by remember { mutableStateOf(true) }

    Column {
        HomeHeaderWitchButtons(
            currency = userModel.currency.toCurrency(),
            spendCurrency = userModel.spendCurrency.toCurrency(),
            earningCurrency = userModel.earningCurrency.toCurrency(),
            backgroundColor = if (userModel.currency > BigDecimal.ZERO) GreenBackground else RedBackground,
            showDetails = showDetails,
            lockOnClick = {
                showDetails = !showDetails
            },
            earningsOnClick = {
                earningClick.invoke()
            },
            spendOnClick = {
                spendClick.invoke()
            }
        )

        Spacer(modifier = Modifier.size(5.dp))

        dealsList?.let { list ->
            val grouped = list.sortedByDescending { it.date }.groupBy { it.date }
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 37.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                grouped.forEach {
                    stickyHeader {
                        ContentListTittle(it.key)
                    }
                    items(
                        items = it.value
                    ) { deal ->
                        DealCard(
                            deal = deal,
                            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                            onClick = dealClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun ContentListTittle(dealDate: String) {
    Text(
        text = "${
            dealDate.getMouthName().uppercase()
        } - ${
            Pair(
                dealDate.getDayName().replaceFirstChar {
                    it.uppercase()
                },
                dealDate.substring(0, 2)
            ).combineStringWithDot()
        }",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(WhiteBackground20),
        style = Typography.titleMedium
    )
}

@Preview(showBackground = true)
@Composable
fun DealsContentListPreview() {
    GastuTheme {
        Column {
            DealsContentList(
                UserModel(
                    0.toString(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO
                ),
                Deals.dealsList.sortedByDescending { it.date }
            )
        }
    }
}