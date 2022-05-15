package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

data class QuadrantItem(
    val title: String,
    val description: String,
    val backgroundColor: Color,
    val alignment: Alignment
)

private val quadrants = listOf(
    QuadrantItem(
        title = "Text composable",
        description = "Displays text and follows Material Design guidelines.",
        backgroundColor = Color.Green,
        alignment = Alignment.TopStart
    ),
    QuadrantItem(
        title = "Image composable",
        description = "Creates a composable that lays out and draws a given Painter class object.",
        backgroundColor = Color.Yellow,
        alignment = Alignment.TopEnd
    ),
    QuadrantItem(
        title = "Row composable",
        description = "A layout composable that places its children in a horizontal sequence.",
        backgroundColor = Color.Cyan,
        alignment = Alignment.BottomStart
    ),
    QuadrantItem(
        title = "Column composable",
        description = "A layout composable that places its children in a vertical sequence.",
        backgroundColor = Color.LightGray,
        alignment = Alignment.BottomEnd
    )
)

@Composable
fun ComposeQuadrant(
    data: List<QuadrantItem> = quadrants
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        for (quadrant in data) {
            QuadrantItemCard(
                title = quadrant.title,
                description = quadrant.description,
                backgroundColor = quadrant.backgroundColor,
                modifier = Modifier
                    .align(quadrant.alignment)
                    .fillMaxSize(0.5f)
            )
        }
    }
}

@Composable
fun QuadrantItemCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(backgroundColor)
                .padding(all = 16.dp)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = description,
                textAlign = TextAlign.Justify,
                color = Color.Black
            )
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun ComposeQuadrantPreview() {
    ExploringJetpackComposeTheme {
        ComposeQuadrant()
    }
}