package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy

import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random.NumberGenerationStrategy
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random.RandomNumberGenerationStrategy

private const val TAG = "RandomMultiClickStrateg"

class RandomMultiClickStrategy(
    numberGenerationStrategy: NumberGenerationStrategy =
        RandomNumberGenerationStrategy(from = 2, to = 4),
    private val runnable: () -> Unit
) : OnClickStrategy {

    private var counter = 0

    private val times = (2..4).random() // numberGenerationStrategy.getNumber()

    override fun onClick() {
        incrementClickCounter()

        if (counter == times) {
            resetClickCounter()
            runnable()
        }
    }

    private fun incrementClickCounter() {
        counter++
    }

    private fun resetClickCounter() {
        counter = DEFAULT_CLICKS
    }

    companion object {
        private const val DEFAULT_CLICKS = 0
    }
}