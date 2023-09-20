package com.paixao.dev.gastu.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.gastu.ui.theme.GastuTheme
import com.paixao.dev.gastu.ui.theme.RedAlert
import com.paixao.dev.gastu.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextWithPriority(
    value: String = "",
    hint: String,
    hasRequired: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onChange: (String) -> Unit = {}
) {
    Column(
        Modifier
            .padding(0.dp, 5.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = value,
            visualTransformation = visualTransformation,
            textStyle = Typography.bodyLarge,
            keyboardOptions = keyboardOptions,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            singleLine = true,
            label = {
                Text(hint)
            },
            supportingText = {
                if (hasRequired) {
                    Text("Obrigatória", color = RedAlert)
                }
            },
            onValueChange = {
                onChange.invoke(it)
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextWithPriority(
    value: TextFieldValue = TextFieldValue(text = ""),
    hint: String,
    hasRequired: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onChange: (TextFieldValue) -> Unit = {}
) {
    Column(
        Modifier
            .padding(0.dp, 5.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = value,
            visualTransformation = visualTransformation,
            textStyle = Typography.bodyLarge,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(hint)
            },
            supportingText = {
                if (hasRequired) {
                    Text("Obrigatória", color = RedAlert)
                }
            },

            onValueChange = {
                onChange.invoke(it)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditTextWithPriorityPreview() {
    GastuTheme {
        EditTextWithPriority("", "hint", true) {
        }
    }
}
