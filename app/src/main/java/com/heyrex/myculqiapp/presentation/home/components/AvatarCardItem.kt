package com.heyrex.myculqiapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.heyrex.myculqiapp.presentation.common.composables.AppWelcomeCard
import com.heyrex.myculqiapp.presentation.common.composables.theme.textSimple

@Composable
fun AvatarCardItem(email: String, name: String, image: String) {
    AppWelcomeCard {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = image,
                contentDescription = name,
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                    .background(Color.Gray.copy(alpha = .1f), RoundedCornerShape(8.dp))
            )
            VerticalDivider(
                modifier = Modifier
                    .height(0.dp),
                thickness = 16.dp
            )
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = name,
                    style = textSimple.copy(color = Color.White, fontSize = 24.sp)
                )
                Text(
                    text = email,
                    style = textSimple.copy(color = Color.LightGray, fontSize = 16.sp)
                )
            }
        }
    }
}

@Preview
@Composable
fun AvatarCardItemPreview() {
    AvatarCardItem(
        email = "mary.smith@yopmail.com",
        name = "Mary Smith",
        image = "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokedex/8/85/Misty_Togepi.png"
    )
}