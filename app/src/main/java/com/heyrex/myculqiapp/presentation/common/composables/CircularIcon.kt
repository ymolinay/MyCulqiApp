package com.heyrex.myculqiapp.presentation.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LaptopMac
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularIcon(
    imageVector: ImageVector,
    backgroundColor: Color,
    iconColor: Color,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .background(backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.size(size / 2)
        )
    }
}

@Preview
@Composable
fun CircularIconPreview() {
    CircularIcon(
        imageVector = Icons.Default.LaptopMac,
        backgroundColor = Color.Red,
        iconColor = Color.Green
    )
}