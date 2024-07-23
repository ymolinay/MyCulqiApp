package com.heyrex.myculqiapp.presentation.common.composables.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.heyrex.myculqiapp.R

val myCustomFont = FontFamily(
    Font(R.font.sourcedanspro_light, FontWeight.Light),
    Font(R.font.sourcesanspro_regular, FontWeight.Normal),
    Font(R.font.sourcesanspro_semibold, FontWeight.SemiBold)
)

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val titleBold = TextStyle(
    fontFamily = myCustomFont,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    color = Color.White
)

val textSimple = TextStyle(
    fontFamily = myCustomFont,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = Color.White
)