package com.paixao.dev.gastu.ui.composable.layout

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.ui.composable.SimpleCurrencyForm
import com.paixao.dev.gastu.ui.composable.HeaderTittleWithCustomIconButton
import com.paixao.dev.gastu.ui.theme.BottomSheetBackground
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.utils.ZERO_FLOAT
import java.lang.Float.max
import kotlin.math.min


@Composable
fun BottomSheetLayoutContent(
    tittle: String,
    bottomSheetContent: @Composable (ColumnScope) -> Unit,
    onClose: () -> Unit = {},
) {
    Surface(
        color = BottomSheetBackground,
        tonalElevation = 20.dp
    ) {
        BoxWithConstraints {
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier.fillMaxHeight()
            ) {
                Box {
                    BottomSheetItem(
                        tittle = tittle,
                        content = bottomSheetContent, onClose = {
                            onClose.invoke()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomSheetItem(
    tittle: String = "",
    onClose: () -> Unit = {},
    content: @Composable (ColumnScope) -> Unit
) {
    var contentH = 480f
    var offsetYPos by remember { mutableStateOf(ZERO_FLOAT) }
    Card(
        modifier = Modifier
            .offset(ZERO_FLOAT.dp, offsetYPos.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, offset ->
                        if (change.pressed) {
                            offsetYPos += (offset.y / 3)
                            offsetYPos = max(min(offsetYPos, contentH), ZERO_FLOAT)
                        }
                    },
                    onDragEnd = {
                        offsetYPos = max(min(offsetYPos, contentH), ZERO_FLOAT)

                        if (offsetYPos >= contentH - (contentH / 3)) {
                            onClose.invoke()
                        } else {
                            offsetYPos = ZERO_FLOAT
                        }
                    }
                )
            },
        shape = RoundedCornerShape(
            CornerSize(16.dp),
            CornerSize(16.dp),
            CornerSize(0.dp),
            CornerSize(0.dp)
        )
    ) {
        Column {
            HeaderTittleWithCustomIconButton(tittle, onClick = { onClose.invoke() })
            Column(
                content = content,
                verticalArrangement = Arrangement.Bottom
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    GastuTheme {
        BottomSheetLayoutContent(
            tittle = "Botton Sheet Preview",
            bottomSheetContent = {
                Column() {
                    SimpleCurrencyForm(DealTypeEnum.Spend) {}
                }
            }
        )
    }
}