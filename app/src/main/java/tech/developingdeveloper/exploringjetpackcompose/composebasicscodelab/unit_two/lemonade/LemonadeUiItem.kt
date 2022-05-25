package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.OnClickStrategy

data class LemonadeUiItem(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val contentDescriptionRes: Int,
    val onClickStrategy: OnClickStrategy = OnClickStrategy.None
)
