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
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.HomeModel
import com.paixao.dev.gastu.presentation.model.mock.Deals
import com.paixao.dev.gastu.presentation.viewmodel.HomeViewModel
import com.paixao.dev.gastu.ui.composable.BottomSheetForm
import com.paixao.dev.gastu.ui.composable.HomeHeaderWitchButtons
import com.paixao.dev.gastu.ui.composable.card.DealCard
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.GreenBackground
import com.paixao.dev.gastu.ui.theme.RedBackground
import com.paixao.dev.gastu.ui.theme.Typography
import com.paixao.dev.gastu.ui.theme.WhiteBackground20
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchHome()
        setContent {
            GastuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = WhiteBackground20
                ) {
                    val homeUiState = viewModel.homeState.collectAsState()
                    DealsContentList(homeUiState.value.homeModel,
                        earningClick = {},
                        spendClick = {},
                        onSave = {
                            viewModel.addDeal(it)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun DealsContentList(
    homeModel: HomeModel,
    earningClick: () -> Unit = {},
    spendClick: () -> Unit = {},
    onSave: (DealModel) -> Unit = {}
) {
    var showDetails by remember { mutableStateOf(true) }
    var isEditing by remember { mutableStateOf(false) }
    var editingType by remember { mutableStateOf(DealTypeEnum.Spend) }

    Column {
        HomeHeaderWitchButtons(
            currency = homeModel.currency.toCurrency(),
            spendCurrency = homeModel.spendCurrency.toCurrency(),
            earningCurrency = homeModel.earningCurrency.toCurrency(),
            backgroundColor = if (homeModel.currency > 0) GreenBackground else RedBackground,
            showDetails = showDetails,
            lockOnClick = {
                showDetails = !showDetails
            },
            earningsOnClick = {
                isEditing = true
                editingType = DealTypeEnum.Earning
                earningClick.invoke()
            },
            spendOnClick = {
                isEditing = true
                editingType = DealTypeEnum.Spend
                spendClick.invoke()
            }
        )

        if (isEditing)
            BottomSheetForm(editingType) {
                isEditing = false
                onSave.invoke(it)
            }

        Spacer(modifier = Modifier.size(5.dp))

        homeModel.deals?.let { list ->
            val grouped = list.groupBy { it.info.date }
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
                        DealCard(deal = deal, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp))
                    }
                }
            }
        }
    }
}

@Composable
internal fun ContentListTittle(dealDate: String) {
    Text(
        Pair(dealDate.getDayName(), dealDate.substring(0, 2)).combineStringWithDot(),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(WhiteBackground20),
        style = Typography.titleMedium
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GastuTheme {
        Column {
            DealsContentList(
                HomeModel(
                    0f,
                    0f,
                    0f,
                    Deals.dealsList.sortedByDescending { it.info.date })
            )
        }
    }
}