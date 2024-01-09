package tech.developingdeveloper.exploringjetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tech.developingdeveloper.exploringjetpackcompose.cameraapp.CAMERA_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.COMPOSE_ARTICLE_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.COMPOSE_QUADRANT_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.TASK_MANAGER_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one.business_card.BUSINESS_CARD_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.DICE_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LEMONADE_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.devfestnoida.form.USER_DETAILS_FORM_UNLABELLED_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.login.LOGIN_SCREEN_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.newsapp.NEWS_ROUTE
import tech.developingdeveloper.exploringjetpackcompose.permissions.RUNTIME_PERMISSIONS_ROUTE

const val HOME_ROUTE = "home"

@Composable
fun HomeScreen(navController: NavController) {

    val items = listOf(
        "Philip Content" to PHILIP_CONTENT_ROUTE,
        "Hit And Try Content" to HIT_AND_TRY_CONTENT_ROUTE,
        "Compose Article" to COMPOSE_ARTICLE_ROUTE,
        "Task Manager" to TASK_MANAGER_ROUTE,
        "Compose Quadrant" to COMPOSE_QUADRANT_ROUTE,
        "Business Card" to BUSINESS_CARD_ROUTE,
        "Dice App" to DICE_ROUTE,
        "Lemonade App" to LEMONADE_ROUTE,
        "Login Screen" to LOGIN_SCREEN_ROUTE,
        "News App" to NEWS_ROUTE,
        "Runtime Permissions App" to RUNTIME_PERMISSIONS_ROUTE,
        "Camera App" to CAMERA_ROUTE,
        "User Details Form Unlabelled" to USER_DETAILS_FORM_UNLABELLED_ROUTE,

        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        items.forEach {
            ElevatedButton(
                onClick = { navController.navigate(it.second) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(it.first)
            }
        }
    }
}