package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy

interface OnClickStrategy {
    fun onClick(): Unit

    object None: OnClickStrategy {
        override fun onClick(): Unit = Unit
    }
}