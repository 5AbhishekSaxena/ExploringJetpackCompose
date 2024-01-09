package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random

import kotlin.random.Random
import kotlin.random.nextInt

class RandomNumberGenerationStrategy(
    private var from: Int,
    private var to: Int,
) : NumberGenerationStrategy {

    init {
        require(from <= to) { "'from'' should be less than or equal to 'to'" }
    }

    override fun getNumber(): Int {
        return Random.nextInt(from..to)
    }
}