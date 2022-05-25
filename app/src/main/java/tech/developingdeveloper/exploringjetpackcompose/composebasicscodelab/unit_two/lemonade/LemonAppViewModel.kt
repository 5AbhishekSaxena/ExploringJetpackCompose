package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.developingdeveloper.exploringjetpackcompose.R
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.DefaultOnClickStrategy
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.RandomMultiClickStrategy

class LemonAppViewModel : ViewModel() {

    private var selectedIndex = 0
        set(value) {
            field = value
            updateCurrentLemonade()
        }

    private val onClickRunnable: () -> Unit = {
        if (selectedIndex in 0..2)
            selectedIndex++
        else
            selectedIndex = 0
    }

    private val defaultOnClickStrategy = DefaultOnClickStrategy(runnable = onClickRunnable)

    private val randomClickStrategy = RandomMultiClickStrategy(runnable = onClickRunnable)

    private val lemonades = listOf(
        LemonadeUiItem(
            titleRes = R.string.tap_the_lemon_tree_to_select_a_lemon,
            imageRes = R.drawable.lemon_tree,
            contentDescriptionRes = R.string.lemon_tree,
            onClickStrategy = defaultOnClickStrategy
        ),
        LemonadeUiItem(
            titleRes = R.string.keep_tapping_the_lemon_to_squeeze_it,
            imageRes = R.drawable.lemon_squeeze,
            contentDescriptionRes = R.string.lemon,
            onClickStrategy = randomClickStrategy
        ),
        LemonadeUiItem(
            titleRes = R.string.tap_the_lemonade_to_drink_it,
            imageRes = R.drawable.lemon_drink,
            contentDescriptionRes = R.string.glass_of_lemonade,
            onClickStrategy = defaultOnClickStrategy
        ),
        LemonadeUiItem(
            titleRes = R.string.empty_glass,
            imageRes = R.drawable.lemon_restart,
            contentDescriptionRes = R.string.empty_glass,
            onClickStrategy = defaultOnClickStrategy
        ),
    )

    private val _currentLemonade: MutableStateFlow<LemonadeUiItem> =
        MutableStateFlow(lemonades[selectedIndex])
    val currentLemonadeUiItem: StateFlow<LemonadeUiItem> = _currentLemonade.asStateFlow()

    private fun updateCurrentLemonade() {
        _currentLemonade.value = lemonades[selectedIndex]
    }

}