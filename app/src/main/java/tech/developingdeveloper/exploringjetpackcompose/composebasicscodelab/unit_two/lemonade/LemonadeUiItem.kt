package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade

import androidx.annotation.DrawableRes
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.OnClickStrategy

data class LemonadeUiItem(
    val title: String,
    @DrawableRes val imageRes: Int,
    val contentDescription: String,
    val onClickStrategy: OnClickStrategy = OnClickStrategy.None
)
