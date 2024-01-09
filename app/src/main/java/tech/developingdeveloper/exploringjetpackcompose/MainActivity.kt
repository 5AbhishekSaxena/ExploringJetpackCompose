package tech.developingdeveloper.exploringjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@ExperimentalGetImage
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExploringJetpackComposeApp()
        }
    }
}

@Composable
@ExperimentalGetImage
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
private fun ExploringJetpackComposeApp() {
    ExploringJetpackComposeTheme {
        MainNavHost()
    }
}
