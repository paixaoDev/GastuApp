package com.paixao.dev.gastu.ui.composable


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.ui.theme.GastuTheme

@Composable
fun MouthDatePicker(mouth: String, onClick: () -> Unit = {}) {
    Row(modifier = Modifier.clickable { onClick.invoke() }) {
        Text(text = mouth, color = Color.White)
        Spacer(modifier = Modifier.size(10.dp))
        Icon(Icons.Default.ArrowDropDown, "", tint = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun MouthDatePickerPreview() {
    GastuTheme {
        MouthDatePicker("Maio")
    }
}