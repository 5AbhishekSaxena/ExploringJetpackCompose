package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.business_card

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.business_card.model.Profile
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun ContactDetailsList(
    profiles: List<Profile>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {

        items(profiles) { profile ->
            Divider(color = Color.White)
            ContactDetailsItem(
                profile = profile,
                modifier = Modifier.padding(16.dp)
            )
        }
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
private fun ContactDetailsListPreview() {
    ExploringJetpackComposeTheme {

        val profiles = listOf(
            Profile(
                value = "+11 (123) 444 555 665",
                icon = Icons.Default.Call
            ),
            Profile(
                value = "@john.doe",
                icon = Icons.Default.Share
            ),
            Profile(
                value = "john.doe@gmail.com",
                icon = Icons.Default.Email
            )
        )

        ContactDetailsList(profiles = profiles)
    }
}