package tech.developingdeveloper.exploringjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import tech.developingdeveloper.exploringjetpackcompose.cameraapp.CameraApp
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LemonAppViewModel
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LemonadeApp
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@ExperimentalGetImage
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LemonAppViewModel(application) as T
            }
        }

        val lemonViewModel = ViewModelProvider(this, factory)
            .get<LemonAppViewModel>()

        setContent {
            ExploringJetpackComposeApp(lemonViewModel)
        }
    }
}

@Composable
@ExperimentalGetImage
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
private fun ExploringJetpackComposeApp(lemonViewModel: LemonAppViewModel) {
    ExploringJetpackComposeTheme {
        MainNavHost()
    }
}
