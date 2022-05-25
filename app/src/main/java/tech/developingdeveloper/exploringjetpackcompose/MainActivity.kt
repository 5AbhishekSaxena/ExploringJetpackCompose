package tech.developingdeveloper.exploringjetpackcompose

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.business_card.BusinessCard
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.DiceApp
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LemonAppViewModel
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LemonadeApp
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
//        HitAndTryContent()
//        ComposeArticle()
//        TaskManager()
//        ComposeQuadrant()
//        BusinessCard()
//        DiceApp()
        LemonadeApp(viewModel = LemonAppViewModel(LocalContext.current.applicationContext as Application)) // better way is to inject using hilt - hiltViewModel() or using VMProviders
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
