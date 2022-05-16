package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.business_card

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.business_card.model.Profile
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun BusinessCard() {

    val profiles = listOf(
        Profile(
            value = "+91 8447919879",
            icon = Icons.Default.Call
        ),
        Profile(
            value = "@5AbhishekSaxena",
            icon = Icons.Default.Share
        ),
        Profile(
            value = "5abhisheksaxena@gmail.com",
            icon = Icons.Default.Email
        ),
    )

    Box(
        modifier = Modifier
            .background(color = Color(0xFF003A4A))
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        PrimaryDetails(
            modifier = Modifier
                .align(Alignment.Center)
        )

        ContactDetailsList(
            profiles = profiles,
            modifier = Modifier
                .align(Alignment.BottomCenter)
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
private fun BusinessCardPreview() {
    ExploringJetpackComposeTheme {
        BusinessCard()
    }
}