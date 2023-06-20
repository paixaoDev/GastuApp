package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.extensions.toFloatCurrency
import com.paixao.dev.gastu.presentation.model.DealDescriptionModel
import com.paixao.dev.gastu.presentation.model.DealInfoModel
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.GreenBackground
import com.paixao.dev.gastu.ui.theme.RedBackground
import com.paixao.dev.gastu.ui.util.Mask
import com.paixao.dev.gastu.ui.util.mask
import com.paixao.dev.gastu.ui.util.maskCurrency


@Composable
fun BottomSheetForm(dealType: DealTypeEnum, onSave: (DealModel) -> Unit) {
    Box(
        Modifier
            .background(color = if (dealType == DealTypeEnum.Earning) GreenBackground else RedBackground)
            .padding(10.dp)
    ) {
        var date by remember {
            mutableStateOf(
                TextFieldValue(
                    text = ""
                )
            )
        }
        var value by remember {
            mutableStateOf(
                TextFieldValue(
                    text = ""
                )
            )
        }
        var title by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var hasPaid by remember { mutableStateOf(false) }
        var dealValue = value.text
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .background(Color.White)
            ) {
                EditTextWithPriority(
                    value = date.mask(Mask.DATE_MASK),
                    hint = "data da transação",
                    hasRequired = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                ) { date = it }

                EditTextWithPriority(
                    value = title,
                    hint = "titulo da transação",
                    hasRequired = true
                ) { title = it }

                EditTextWithPriority(
                    value = value.maskCurrency(),
                    hint = "valor da transação",
                    hasRequired = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                ) {
                    dealValue = it.text
                    value = it
                }

                EditTextWithPriority(category, "categoria da transação", false) { category = it }

                EditTextWithPriority(description, "descrição da transação", false) {
                    description = it
                }
                Spacer(modifier = Modifier.size(15.dp))
                Text(text = "Transação foi paga?")
                Switch(checked = hasPaid, onCheckedChange = { hasPaid = !hasPaid })
                Button(

                    onClick = {
                        if (title.isNotBlank() && date.text.isNotBlank()) {
                            onSave.invoke(
                                DealModel(
                                    java.util.UUID.randomUUID().toString(),
                                    java.util.UUID.randomUUID().toString(),
                                    DealInfoModel(
                                        date.text,
                                        value = dealValue.toFloatCurrency() / 100,
                                        hasPaid,
                                        false
                                    ),
                                    DealDescriptionModel(title, description, category),
                                    dealType.name
                                )
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(if (dealType == DealTypeEnum.Earning) GreenBackground else RedBackground)
                ) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetFormPreview() {
    GastuTheme {
        BottomSheetForm(DealTypeEnum.Earning, {})
    }
}