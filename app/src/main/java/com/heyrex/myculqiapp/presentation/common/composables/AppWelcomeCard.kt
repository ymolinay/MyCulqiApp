package com.heyrex.myculqiapp.presentation.common.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heyrex.myculqiapp.presentation.common.composables.theme.cardColors
import com.heyrex.myculqiapp.presentation.common.composables.theme.titleBold

@Composable
fun AppWelcomeCard(
    content: @Composable () -> Unit
) {
    Card(
        colors = cardColors,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.dp, Color.Transparent),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun AppWelcomeCardPreview() {
    AppWelcomeCard {
        Column {
            Text(
                text = "Hola amigo",
                style = titleBold.copy(fontSize = 24.sp),
                modifier = Modifier.padding(16.dp)
            )
            Icon(
                imageVector = Icons.Default.Keyboard,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = "Hola amigo",
                style = titleBold.copy(fontSize = 24.sp),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}