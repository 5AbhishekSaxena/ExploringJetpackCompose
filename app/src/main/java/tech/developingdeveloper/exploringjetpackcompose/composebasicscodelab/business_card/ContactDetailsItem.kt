package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.business_card

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.business_card.model.Profile
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun ContactDetailsItem(
    profile: Profile,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.width(32.dp))

        Image(
            imageVector = profile.icon, contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFF3ddc84))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = profile.value,
            color = Color.White
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun ContactDetailsItemPreview() {
    ExploringJetpackComposeTheme {

        val profile = Profile(
            value = "+11 (123) 444 555 665",
            icon = Icons.Default.Call
        )

        ContactDetailsItem(profile)
    }
}