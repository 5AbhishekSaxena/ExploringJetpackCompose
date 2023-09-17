package tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tech.developingdeveloper.exploringjetpackcompose.droidcon.core.ui.DroidconTheme

@Composable
fun HomeContent() {
    Scaffold(
        topBar = {
            DroidconTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

        }
    }
}

@Composable
private fun DroidconTopAppBar() {
    TopAppBar {
        Text(text = "Droidcon NYC 2023")
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
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun HomeContentPreview() {
    DroidconTheme {
        Surface {
            HomeContent()
        }
    }
}