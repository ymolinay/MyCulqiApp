package com.heyrex.myculqiapp.presentation.common.composables.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecundayColor,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colors = LightColorPalette

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}