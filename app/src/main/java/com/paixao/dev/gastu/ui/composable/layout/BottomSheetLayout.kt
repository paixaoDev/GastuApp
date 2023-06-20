package com.paixao.dev.gastu.ui.composable.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.DealsContentList
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.presentation.model.HomeModel
import com.paixao.dev.gastu.presentation.model.mock.Deals
import com.paixao.dev.gastu.ui.composable.BottomSheetForm
import com.paixao.dev.gastu.ui.composable.TextWithButton
import com.paixao.dev.gastu.ui.theme.BottomSheetBackground
import com.paixao.dev.gastu.ui.theme.GastuTheme

@Composable
fun BottomSheetLayoutContent(
    tittle: String,
    show: Boolean = true,
    content: @Composable (ColumnScope) -> Unit,
    bottomSheetContent: @Composable (ColumnScope) -> Unit,
    onClose: () -> Unit = {},
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()

    ) {

        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxHeight()) {
            Column(content = content)
        }


        BoxWithConstraints() {
            if (show) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(BottomSheetBackground)
                    )
                }
            }

            AnimatedVisibility(
                visible = show,
                enter = slideInVertically(
                    initialOffsetY = {
                        it / 2
                    }
                ),
                exit = slideOutVertically(
                    targetOffsetY = {
                        it / 2
                    }
                )
            ) {
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
}

@Composable
fun BottomSheetItem(
    tittle: String = "",
    hasCloseButton: Boolean = true,
    onClose: () -> Unit = {},
    content: @Composable (ColumnScope) -> Unit
) {
    //TODO - Aqui fica o bottonsheet em si que é apresentado e tem animações e estados
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            CornerSize(16.dp),
            CornerSize(16.dp),
            CornerSize(0.dp),
            CornerSize(0.dp)
        )
    ) {
        Column() {
            TextWithButton(tittle, onClick = { onClose.invoke() })
            Column(content = content, verticalArrangement = Arrangement.Bottom)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GastuTheme {
        BottomSheetLayoutContent(
            "Botton Sheet",
            content = {
                DealsContentList(
                    HomeModel(
                        0f,
                        0f,
                        0f,
                        Deals.dealsList.sortedByDescending { it.info.date })
                )
            }, bottomSheetContent = {
                Column() {
                    BottomSheetForm(DealTypeEnum.Spend) {
                    }
                }
            }
        )
    }
}