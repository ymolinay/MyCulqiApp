package com.heyrex.myculqiapp.presentation.common.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heyrex.myculqiapp.R
import com.heyrex.myculqiapp.presentation.common.composables.theme.textSimple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    typePassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                border = BorderStroke(
                    width = if (isFocused) 2.dp else 0.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .height(56.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = placeholder,
                    style = textSimple.copy(
                        fontSize = if (value.isEmpty()) 14.sp else 12.sp,
                        color = Color.Black
                    )
                )
            },
            singleLine = true,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                cursorColor = Color.Black,
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (typePassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = modifier
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .fillMaxWidth()
                .padding(end = 50.dp)
        )
        if (typePassword) {
            Text(
                text = stringResource(id = if (passwordVisible) R.string.login_password_hide else R.string.login_password_view),
                style = textSimple.copy(color = Color.Black),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { passwordVisible = !passwordVisible }
            )
        }
    }
}

@Preview
@Composable
fun AppTextFieldPreview() {
    AppTextField(
        value = "Mary Smith",
        placeholder = "Nombre",
        onValueChange = {},
    )
}