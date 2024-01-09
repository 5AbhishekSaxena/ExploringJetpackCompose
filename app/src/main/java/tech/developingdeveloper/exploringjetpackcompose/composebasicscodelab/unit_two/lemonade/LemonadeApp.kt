package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

const val LEMONADE_ROUTE = "lemonade"

@Composable
fun LemonadeApp(viewModel: LemonAppViewModel = viewModel()) {

    val currentLemonade by viewModel.currentLemonadeUiItem.collectAsState()

    LemonadeAppContent(lemonadeUiItem = currentLemonade)
}