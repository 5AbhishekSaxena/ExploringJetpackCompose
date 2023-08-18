package tech.developingdeveloper.exploringjetpackcompose.animations

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun AnimatedIdCard() {

    val configuration = LocalConfiguration.current

    var isAnimating by remember { mutableStateOf(false) }
    val xAxis by animateDpAsState(
        targetValue = if (isAnimating)
            configuration.screenWidthDp.minus(10).minus(60).dp
        else
            5.dp
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .offset(x = xAxis)
                .background(Color.Red)
                .size(60.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            isAnimating = !isAnimating
        }) {
            val text = if (isAnimating) "Stop" else "Start"
            Text(text = text)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "isAnimating: $isAnimating")
        Text(text = "offsetX: $xAxis")

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
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun AnimatedIdCardPreview() {
    ExploringJetpackComposeTheme {
        Surface {
            AnimatedIdCard()
        }
    }
}
