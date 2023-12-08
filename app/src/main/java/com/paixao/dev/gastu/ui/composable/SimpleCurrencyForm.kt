package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.domain.util.DealTypeEnum
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.extensions.unMaskValueToBigDecimal
import com.paixao.dev.gastu.presentation.model.DealModel
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.GreenBackground
import com.paixao.dev.gastu.ui.theme.RedBackground
import com.paixao.dev.gastu.ui.util.Mask
import com.paixao.dev.gastu.ui.util.mask
import com.paixao.dev.gastu.ui.util.maskCurrency

@Composable
fun SimpleCurrencyForm(
    dealType: DealTypeEnum,
    deal: DealModel? = null,
    onSave: (DealModel) -> Unit
) {
    Box(
        Modifier.background(color = Color.White)
    ) {
        var date by remember { mutableStateOf(TextFieldValue(text = deal?.date ?: "")) }
        var value by remember {
            mutableStateOf(
                TextFieldValue(
                    text = deal?.value?.toCurrency() ?: ""
                )
            )
        }
        var title by remember { mutableStateOf(deal?.name ?: "") }
        var description by remember { mutableStateOf(deal?.description ?: "") }
        var hasPaid by remember { mutableStateOf(deal?.hasExecuted ?: false) }
        var dealValue = value.text
        Card(
            Modifier.padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Column {
                EditTextWithPriority(
                    value = date.mask(Mask.DATE_MASK),
                    hint = "Data da transação",
                    hasRequired = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                ) { date = it }

                EditTextWithPriority(
                    value = title,
                    hint = "Titulo da transação",
                    hasRequired = true
                ) { title = it }

                EditTextWithPriority(
                    value = value.maskCurrency(),
                    hint = "Valor da transação",
                    hasRequired = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                ) {
                    dealValue = it.text
                    value = it
                }

                EditTextWithPriority(description, "Descrição da transação", false) {
                    description = it
                }

                Spacer(modifier = Modifier.size(15.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Transação foi paga?")
                    Spacer(modifier = Modifier.size(15.dp))
                    Switch(checked = hasPaid, onCheckedChange = { hasPaid = !hasPaid })
                }

                Spacer(modifier = Modifier.size(15.dp))

                Button(
                    onClick = {
                        if (title.isNotBlank() && date.text.isNotBlank()) {
                            onSave.invoke(
                                DealModel(
                                    id = deal?.id ?: java.util.UUID.randomUUID().toString(),
                                    userId = deal?.userId ?: java.util.UUID.randomUUID().toString(),
                                    date = date.text,
                                    value = dealValue.unMaskValueToBigDecimal(),
                                    hasExecuted = hasPaid,
                                    hasFixed = false,
                                    name = title,
                                    description = description,
                                    category = "",
                                    dealType = deal?.dealType ?: dealType.name
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
fun SimpleCurrencyFormPreview() {
    GastuTheme {
        SimpleCurrencyForm(DealTypeEnum.Earning, onSave = {})
    }
}
