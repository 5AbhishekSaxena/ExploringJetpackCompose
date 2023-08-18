package tech.developingdeveloper.exploringjetpackcompose.animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun FileAnimation(
    durationMillis: Long,
    delayMillis: Long,
) {
    var shouldGenerateText by remember { mutableStateOf(true) }
    var generatedText = remember { "" }

    LaunchedEffect(key1 = Unit) {
        delay(delayMillis)
        generatedText = getGeneratedText(600)
        delay(250L)
    }
}

fun getGeneratedText(length: Int): String {
    val sb = StringBuilder()
    val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    repeat(length) {
        val randomNumber = Random.nextInt(until = letters.length)
        sb.append(letters[randomNumber])
    }

    return sb.toString()
}