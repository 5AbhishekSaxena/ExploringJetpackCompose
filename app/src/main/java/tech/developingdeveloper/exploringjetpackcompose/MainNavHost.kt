@file:OptIn(ExperimentalAnimationApi::class)

package tech.developingdeveloper.exploringjetpackcompose

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.developingdeveloper.exploringjetpackcompose.cameraapp.CAMERA_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.cameraapp.CameraApp
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.COMPOSE_ARTICLE_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.ComposeArticle
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.COMPOSE_QUADRANT_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.ComposeQuadrant
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.TASK_MANAGER_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.TaskManager
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.business_card.BUSINESS_CARD_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.business_card.BusinessCard
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.DICE_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.DiceApp
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LEMONADE_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LemonadeApp
import tech.developingdeveloper.exploringjetpackcompose.devfestnoida.form.USER_DETAILS_FORM_UNLABELLED_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.devfestnoida.form.UserDetailsFormUnlabelled
import tech.developingdeveloper.exploringjetpackcompose.login.LOGIN_SCREEN_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.login.LoginScreen
import tech.developingdeveloper.exploringjetpackcompose.newsapp.NEWS_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.newsapp.NewsApp
import tech.developingdeveloper.exploringjetpackcompose.permissions.RUNTIME_PERMISSIONS_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.permissions.RuntimePermissionsApp

@Composable
@ExperimentalGetImage
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(HOME_ROUTE) { HomeScreen(navController) }
        composable(PHILIP_CONTENT_ROUTE) { PhilipContent() }
        composable(HIT_AND_TRY_CONTENT_ROUTE) { HitAndTryContent() }
        composable(COMPOSE_ARTICLE_ROUTE) { ComposeArticle() }
        composable(TASK_MANAGER_ROUTE) { TaskManager() }
        composable(COMPOSE_QUADRANT_ROUTE) { ComposeQuadrant() }
        composable(BUSINESS_CARD_ROUTE) { BusinessCard() }
        composable(DICE_ROUTE) { DiceApp() }
        composable(LEMONADE_ROUTE) { LemonadeApp() }
        composable(LOGIN_SCREEN_ROUTE) { LoginScreen() }
        composable(NEWS_ROUTE) { NewsApp() }
        composable(RUNTIME_PERMISSIONS_ROUTE) { RuntimePermissionsApp() }
        composable(CAMERA_ROUTE) { CameraApp() }
        composable(USER_DETAILS_FORM_UNLABELLED_ROUTE) { UserDetailsFormUnlabelled() }
    }
}