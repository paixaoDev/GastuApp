package com.paixao.dev.gastu.ui.composable.deal

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.presentation.model.UserModel
import com.paixao.dev.gastu.presentation.model.mock.Deals
import com.paixao.dev.gastu.ui.composable.HomeHeaderWitchButtons
import com.paixao.dev.gastu.ui.composable.card.DealCard
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.GreenBackground
import com.paixao.dev.gastu.ui.theme.RedBackground
import java.math.BigDecimal

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun DealsContentList(
    dealsList: List<DealModel>?,
    dealClick: (DealModel) -> Unit = {}
) {
    Box {
        dealsList?.let { list ->
            val grouped = list.sortedByDescending { it.date }.groupBy { it.date }
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = 10.dp,
                    vertical = 15.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                grouped.forEach {
                    stickyHeader {
                        DealStickyHeader(it.key)
                    }
                    items(
                        items = it.value
                    ) { deal ->
                        DealCard(
                            deal = deal,
                            onClick = dealClick
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DealsContentListPreview() {
    GastuTheme {
        DealsContentList(
            Deals.dealsList.sortedByDescending { it.date }
        )
    }
}