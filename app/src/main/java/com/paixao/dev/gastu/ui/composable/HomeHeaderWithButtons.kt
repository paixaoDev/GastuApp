package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.GreenBackground
import com.paixao.dev.gastu.ui.theme.Typography

@Composable
fun HomeHeaderWitchButtons(
    backgroundColor: Color = GreenBackground,
    currency: String,
    spendCurrency: String,
    earningCurrency: String,
    showDetails: Boolean,
    dateOnClick: () -> Unit = {},
    lockOnClick: () -> Unit = {},
    earningsOnClick: () -> Unit = {},
    spendOnClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(backgroundColor),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(
            CornerSize(0.dp),
            CornerSize(0.dp),
            CornerSize(16.dp),
            CornerSize(16.dp)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                //Spacer(modifier = Modifier.size(20.dp))
                //MouthDatePicker("Maio", dateOnClick)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Saldo em conta", style = Typography.bodySmall, color = Color.White)
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = if (showDetails) currency else "               ",
                    style = Typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.background(if (showDetails) Color.Unspecified else Color.LightGray)
                )
                Spacer(modifier = Modifier.size(5.dp))
                Icon(
                    if (showDetails) Icons.Outlined.Lock else Icons.Filled.Lock,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.clickable { lockOnClick.invoke() })
                Spacer(modifier = Modifier.size(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clickable { earningsOnClick.invoke() }
                    ) {
                        DealValueWithType(
                            value = earningCurrency,
                            showDetails = showDetails
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(50.dp)
                            .background(Color.LightGray)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clickable { spendOnClick.invoke() }
                    ) {
                        DealValueWithType(
                            type = DealTypeEnum.Spend,
                            value = spendCurrency,
                            showDetails = showDetails
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeHeaderWitchButtonsPreview() {
    GastuTheme {
        HomeHeaderWitchButtons(
            currency = "",
            earningCurrency = "",
            spendCurrency = "",
            showDetails = false
        )
    }
}