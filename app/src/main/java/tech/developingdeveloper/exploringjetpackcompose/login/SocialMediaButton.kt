package tech.developingdeveloper.exploringjetpackcompose.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.login.theme.BlueGray
import tech.developingdeveloper.exploringjetpackcompose.login.theme.LightBlueWhite

@Composable
fun SocialMediaButton(
    text: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .socialMedia()
            .clickable(onClick = onClick),
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFF647488)
            )
        )
    }
}

private fun Modifier.socialMedia(): Modifier = composed {
    if (isSystemInDarkTheme())
        background(Color.Transparent)
            .border(
                width = 1.dp,
                color = BlueGray,
                shape = RoundedCornerShape(4.dp)
            ) else
        background(LightBlueWhite)
}