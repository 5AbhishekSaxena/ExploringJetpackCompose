package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.random

class FixedNumberGenerationStrategy(
    private val numberOfClicks: Int
) : NumberGenerationStrategy {
    override fun getNumber(): Int {
        return numberOfClicks
    }
}