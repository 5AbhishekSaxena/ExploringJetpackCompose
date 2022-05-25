package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun LemonadeApp(viewModel: LemonAppViewModel) {

    val currentLemonade by viewModel.currentLemonadeUiItem.collectAsState()

    LemonadeAppContent(lemonadeUiItem = currentLemonade)
}