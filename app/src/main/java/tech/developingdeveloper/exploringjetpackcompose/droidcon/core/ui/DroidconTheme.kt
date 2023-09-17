package tech.developingdeveloper.exploringjetpackcompose.droidcon.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun DroidconTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    ExploringJetpackComposeTheme(darkTheme = darkTheme, content = content)
}