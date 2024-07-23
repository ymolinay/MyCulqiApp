package com.heyrex.myculqiapp.presentation.common.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppTextFieldPassword(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    AppTextField(
        value = value,
        placeholder = placeholder,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        typePassword = true,
        modifier = modifier,
    )
}

@Preview
@Composable
fun AppTextFieldAppTextFieldPasswordPreview() {
    AppTextFieldPassword(
        placeholder = "Nombre",
        value = "Mary Smith",
        onValueChange = {},
    )
}