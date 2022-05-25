package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random

import kotlin.random.Random
import kotlin.random.nextInt

class RandomNumberGenerationStrategy(
    private var from: Int,
    private var to: Int
): NumberGenerationStrategy {

    init {
        if (from > to) {
            // 5, 2
            from += to // 7, 2
            to = from - to // 7, 5
            from -= to // 2. 5
        }
    }

    override fun getNumber(): Int {
        return Random.nextInt(from..to)
    }
}