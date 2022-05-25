package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy

class DefaultOnClickStrategy(
    private val runnable: () -> Unit
) : OnClickStrategy {
    override fun onClick(): Unit = runnable()
}