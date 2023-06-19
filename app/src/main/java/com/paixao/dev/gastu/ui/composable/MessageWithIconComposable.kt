package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.Typography

@Composable
fun MessageWithIcon(
    message: String,
    icon: ImageVector?,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.End,
    textColor: Color = Color.Unspecified,
    iconColor: Color = Color.Unspecified,
    iconModifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(0.dp, 5.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = message,
            style = Typography.labelLarge,
            maxLines = 1,
            color = textColor,
            fontSize = 16.sp
        )
        if (icon != null)
            Icon(icon,
                contentDescription = "",
                tint = iconColor,
                modifier = iconModifier.size(14.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MessageWithIconPreview() {
    GastuTheme {
        MessageWithIcon("R$ 0000000,00", Icons.Default.Warning, horizontalAlignment = Alignment.End)
    }
}
