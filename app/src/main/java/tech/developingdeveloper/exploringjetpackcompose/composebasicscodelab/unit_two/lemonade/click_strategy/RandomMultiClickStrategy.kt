package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy

import android.util.Log
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random.NumberGenerationStrategy
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random.RandomNumberGenerationStrategy

class RandomMultiClickStrategy(
    private val numberGenerationStrategy: NumberGenerationStrategy =
        RandomNumberGenerationStrategy(from = 2, to = 4),
    private val runnable: () -> Unit
) : OnClickStrategy {

    private var clicks = 0

    private var requiredClicks = 0

    override fun onClick() {
        Log.e(javaClass.name, "onClick, clicks: $clicks, requiredClicks: $requiredClicks")
        if (clicks == 0) {
            refresh()
        }

        if (clicks == requiredClicks) {
            reset()
            runnable()
        }

        incrementClicks()
    }

    private fun refresh() {
        requiredClicks = numberGenerationStrategy.getNumber()
        Log.e(javaClass.name, "refresh, requiredClicks: $requiredClicks")
    }

    private fun reset() {
        Log.e(javaClass.name, "reset called")
        clicks = 0
    }

    private fun incrementClicks() {
        clicks++
    }
}