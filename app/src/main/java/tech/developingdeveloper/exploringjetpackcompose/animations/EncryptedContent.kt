package tech.developingdeveloper.exploringjetpackcompose.animations

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme
import kotlin.random.Random

@Composable
fun EncryptedContent(
    encryptedText: String,
    durationMillis: Long,
    delayMillis: Long,
    modifier: Modifier = Modifier,
) {
    val chunkSize = 12
    val chunks = remember { encryptedText.split("").chunked(chunkSize) }

    repeat(chunkSize) { col ->
        Column {
            for (row in chunks[col]) {
                for (letter in row) {
                    Text(
                        text = row,
                        color = if (Random.nextDouble() < 0.4)
                            Color.White.copy(alpha = 0.7f)
                        else
                            Color(0xFF6B6B6B)
                    )
                }
            }
        }
    }

}

//@Preview(
//    name = "Night Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    widthDp = 600,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun EncryptedContentPreview() {
    ExploringJetpackComposeTheme {
        Surface {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                EncryptedContent(
                    encryptedText = getGeneratedText(600),
                    durationMillis = 600,
                    delayMillis = 600,
                )
            }
        }
    }
}