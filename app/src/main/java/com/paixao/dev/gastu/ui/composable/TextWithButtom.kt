package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.Typography

@Composable
fun HeaderTittleWithCustomIconButton(
    text: String,
    buttonIcon: ImageVector = Icons.Default.Close,
    buttonContainerColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = Typography.titleMedium)
        TextButton(
            onClick = { onClick.invoke() },
        ) {
            Icon(buttonIcon, "", tint = buttonContainerColor)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GastuTheme {
        HeaderTittleWithCustomIconButton("Titulo")
    }
}
