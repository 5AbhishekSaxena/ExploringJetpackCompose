package tech.developingdeveloper.exploringjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExploringJetpackComposeApp()
        }
    }
}

@Composable
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
private fun ExploringJetpackComposeApp() {
    ExploringJetpackComposeTheme {
        // PhilipContent()
        HitAndTryContent()
    }
}

@Composable
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
fun DefaultPreview() {
    ExploringJetpackComposeApp()
}
