package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade

import android.app.Application
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.DefaultOnClickStrategy
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.RandomMultiClickStrategy

class LemonAppViewModel(
    application: Application,
    lemonadeRepository: LemonadeRepository = LemonadeRepository(),
) : AndroidViewModel(application) {

    private var selectedIndex = 0
        set(value) {
            field = value
            onSelectedIndexUpdated()
        }

    private val onClickRunnable: () -> Unit = { selectedIndex++ }

    private val defaultOnClickStrategy = DefaultOnClickStrategy(runnable = onClickRunnable)

    private val randomClickStrategy = RandomMultiClickStrategy(runnable = onClickRunnable)

//    private val lemonades = listOf(
//        LemonadeUiItem(
//            title = getString(R.string.tap_the_lemon_tree_to_select_a_lemon), // R.string.tap_the_lemon_tree_to_select_a_lemon,
//            imageRes = R.drawable.lemon_tree,
//            contentDescription = getString(R.string.lemon_tree),
//            onClickStrategy = defaultOnClickStrategy
//        ),
//        LemonadeUiItem(
//            title = getString(R.string.keep_tapping_the_lemon_to_squeeze_it),
//            imageRes = R.drawable.lemon_squeeze,
//            contentDescription = getString(R.string.lemon),
//            onClickStrategy = randomClickStrategy
//        ),
//        LemonadeUiItem(
//            title = getString(R.string.tap_the_lemonade_to_drink_it),
//            imageRes = R.drawable.lemon_drink,
//            contentDescription = getString(R.string.glass_of_lemonade),
//            onClickStrategy = defaultOnClickStrategy
//        ),
//        LemonadeUiItem(
//            title = getString(R.string.empty_glass),
//            imageRes = R.drawable.lemon_restart,
//            contentDescription = getString(R.string.empty_glass),
//            onClickStrategy = defaultOnClickStrategy
//        ),
//    )

    private val lemonades = lemonadeRepository.getLemons()
        .map {
            LemonadeUiItem(
                title = it.title,
                imageRes = it.imageUrl,
                contentDescription = it.contentDescription,
                onClickStrategy = when (it.clickStrategy) {
                    "random" -> randomClickStrategy
                    else -> defaultOnClickStrategy
                }
            )
        }

    private val _currentLemonade: MutableStateFlow<LemonadeUiItem> =
        MutableStateFlow(lemonades[selectedIndex])
    val currentLemonadeUiItem: StateFlow<LemonadeUiItem> = _currentLemonade.asStateFlow()

    private fun onSelectedIndexUpdated() {
        resetIfLastLemonade()
        updateCurrentLemonade()
    }

    private fun updateCurrentLemonade() {
        _currentLemonade.value = lemonades[selectedIndex]
    }

    private fun resetIfLastLemonade() {
        if (lemonades.isEmpty())
            resetSelectedIndex()

        val isLastItem = selectedIndex == lemonades.size
        if (isLastItem)
            resetSelectedIndex()
    }

    private fun resetSelectedIndex() {
        selectedIndex = 0
    }

    private fun getString(@StringRes stringRes: Int): String {
        return getApplication<Application>().getString(stringRes)
    }
}
