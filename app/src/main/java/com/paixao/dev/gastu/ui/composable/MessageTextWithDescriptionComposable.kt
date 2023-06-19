package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paixao.dev.gastu.extensions.inLineString
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.Typography

@Composable
fun MessageTextWithMinorDescription(title: String, description: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxHeight().padding(0.dp, 5.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, style = Typography.labelLarge, maxLines = 1, modifier = Modifier.fillMaxWidth(), fontSize = 16.sp)
        Text(text = description.inLineString(), style = Typography.bodyMedium, maxLines = 1, modifier = Modifier.fillMaxWidth(), color = Color.Gray, fontSize = 12.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun MessageTextWithMinorDescriptionPreview() {
    GastuTheme {
        MessageTextWithMinorDescription(
            "Android",
            listOf("Lazer", "Contas de gastos referentes ao mes de abril", "Fixa")
        )
    }
}

