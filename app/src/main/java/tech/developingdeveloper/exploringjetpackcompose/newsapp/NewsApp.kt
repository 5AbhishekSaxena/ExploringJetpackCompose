package tech.developingdeveloper.exploringjetpackcompose.newsapp

import androidx.compose.runtime.Composable
import tech.developingdeveloper.exploringjetpackcompose.newsapp.presentation.onboarding.OnBoardingScreen

const val NEWS_ROUTE = "news"

@Composable
fun NewsApp() {
    OnBoardingScreen()
}