package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade

import tech.developingdeveloper.exploringjetpackcompose.R

class LemonadeRepository {

    private val lemonResponse = listOf(
        LemonResponseItem(
            title = "Tap the lemon tree to select a lemon",
            imageUrl = R.drawable.lemon_tree,
            contentDescription = "Lemon Tree",
            clickStrategy = "single",
        ),
        LemonResponseItem(
            title = "Keep tapping the lemon to squeeze it",
            imageUrl = R.drawable.lemon_squeeze,
            contentDescription = "Lemon",
            clickStrategy = "random",
        ),
        LemonResponseItem(
            title = "Tap the lemonade to drink it",
            imageUrl = R.drawable.lemon_drink,
            contentDescription = "Glass of lemonade",
            clickStrategy = "single",
        ),
        LemonResponseItem(
            title = "Tap the empty glass to start again",
            imageUrl = R.drawable.lemon_restart,
            contentDescription = "Empty glass",
            clickStrategy = "single",
        ),
    )

    fun getLemons(): List<LemonResponseItem> {
        return lemonResponse
    }
}