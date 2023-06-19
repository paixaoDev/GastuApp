package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
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

@Composable
internal fun DealValueWithType(
    type: DealTypeEnum = DealTypeEnum.Earning,
    value: String,
    showDetails: Boolean = true
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Icon(
            if (type == DealTypeEnum.Earning) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
            contentDescription = "",
            tint = if (type == DealTypeEnum.Earning) Color.Green else Color.Red,
            modifier = Modifier
                .size(54.dp)
                .padding(start = 5.dp)
        )
        Column {
            Text(text = if (type == DealTypeEnum.Earning) "Receitas" else "Despesas")
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = if (showDetails) value else "               ",
                color = if (type == DealTypeEnum.Earning) Color.Green else Color.Red,
                modifier = Modifier.background(if (showDetails) Color.Unspecified  else Color.LightGray)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DealValueWithTypePreview() {
    GastuTheme {
        DealValueWithType(value = "")
    }
}