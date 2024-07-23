package com.heyrex.myculqiapp.presentation.common.composables

import android.content.res.Resources.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    colorText: Color = Color.Black,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    shape: CornerBasedShape = RoundedCornerShape(8.dp),
    startIcon: ImageVector? = null,
    startIconColor: Color? = null,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        elevation = null,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f)
        ),
        border = border,
        shape = shape
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            startIcon?.let { icon ->
                Row(
                    modifier = Modifier.align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = startIconColor ?: MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(
                text = text,
                color = colorText,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewAppButton() {
    AppButton(
        text = "Iniciar sesión",
        startIcon = Icons.Default.Facebook,
        enabled = true,
        onClick = {})
}