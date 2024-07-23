package com.heyrex.myculqiapp.presentation.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import com.heyrex.myculqiapp.R

@Composable
fun AppWelcomeBack(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val bitmap = remember {
        context.resources.getDrawable(R.drawable.wall, null).toBitmap()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        content()
    }
}

@Preview
@Composable
fun AppWelcomeBackPreview() {
    AppWelcomeBack {
        Text(text = "Hola amigo")
    }
}