package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy

import android.util.Log
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random.NumberGenerationStrategy
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random.RandomNumberGenerationStrategy

private const val TAG = "RandomMultiClickStrateg"

class RandomMultiClickStrategy(
    private val numberGenerationStrategy: NumberGenerationStrategy =
        RandomNumberGenerationStrategy(from = 2, to = 4),
    private val runnable: () -> Unit
) : OnClickStrategy {

    private var clicks = DEFAULT_CLICKS

    private var requiredClicks = 0

    override fun onClick() {
        Log.e(TAG, "onClick, clicks: $clicks, requiredClicks: $requiredClicks")
        val requiresInitialisation = clicks == DEFAULT_CLICKS
        if (requiresInitialisation) {
            initialise()
        }

        val shouldExecuteRunnable = clicks == (requiredClicks - 1)
        if (shouldExecuteRunnable) {
            reset()
            runnable()
            return
        }

        incrementClicks()
    }

    private fun initialise() {
        requiredClicks = numberGenerationStrategy.getNumber()
        Log.e(TAG, "setup, requiredClicks: $requiredClicks")
    }

    private fun reset() {
        clicks = DEFAULT_CLICKS
        Log.e(TAG, "reset called, clicks: $clicks")
    }

    private fun incrementClicks() {
        clicks++
    }

    companion object {
        private const val DEFAULT_CLICKS = 0
    }
}