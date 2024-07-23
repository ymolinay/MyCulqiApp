package com.heyrex.myculqiapp.presentation.common.composables

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
    height: Dp = 4.dp
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = height,
        color = Color.Transparent
    )
}