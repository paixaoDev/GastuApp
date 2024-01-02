package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paixao.dev.gastu.extensions.getActualDate
import com.paixao.dev.gastu.extensions.getMouthName
import com.paixao.dev.gastu.extensions.minusActualDate
import com.paixao.dev.gastu.extensions.plusActualDate
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.WhiteBackground20
import com.paixao.dev.gastu.ui.theme.YellowBackground


@Composable
fun DateSwipeChangeButton(date: String, hasRequired: Boolean = false) {
    val sensibility = 20
    var dateTime by remember { mutableStateOf(date) }
    var amount by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .background(WhiteBackground20)
            .size(100.dp)
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    amount += dragAmount.x
                    if (amount > sensibility) {
                        dateTime = dateTime.plusActualDate()
                        amount = 0f
                    } else if (amount < -sensibility) {
                        dateTime = dateTime.minusActualDate()
                        amount = 0f
                    }
                }
            }

    ) {
        if (hasRequired) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp, 5.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(YellowBackground)
                        .padding(6.dp, 2.dp)
                        .focusable(false),
                ) {
                    Text(
                        text = "i",
                        fontSize = 8.sp,
                        color = Color.White
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(5.dp, 0.dp)) {
            Text(
                text = dateTime.substring(6, date.length),
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = dateTime.getMouthName().capitalize(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = dateTime.substring(0, 2),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DateSwipeChangeButtonPreview() {
    GastuTheme {
        DateSwipeChangeButton(getActualDate())
    }
}