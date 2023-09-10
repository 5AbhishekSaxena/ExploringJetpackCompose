package tech.developingdeveloper.exploringjetpackcompose.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import tech.developingdeveloper.exploringjetpackcompose.R

data class OnBoardingPage(
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int,
)

val pages = listOf(
    OnBoardingPage(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        imageRes = R.drawable.news_onboarding1
    ),
    OnBoardingPage(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        imageRes = R.drawable.news_onboarding2
    ),
    OnBoardingPage(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        imageRes = R.drawable.news_onboarding3
    )
)
