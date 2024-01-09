package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_one

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    val backgroundColor: Color
)

private val quadrants = listOf(
    QuadrantItem(
        title = "Text composable",
        description = "Displays text and follows Material Design guidelines.",
        backgroundColor = Color.Green
    ),
    QuadrantItem(
        title = "Image composable",
        description = "Creates a composable that lays out and draws a given Painter class object.",
        backgroundColor = Color.Yellow
    ),
    QuadrantItem(
        title = "Row composable",
        description = "A layout composable that places its children in a horizontal sequence.",
        backgroundColor = Color.Cyan
    ),
    QuadrantItem(
        title = "Column composable",
        description = "A layout composable that places its children in a vertical sequence.",
        backgroundColor = Color.LightGray
    ),
    QuadrantItem(
        title = "Column composable",
        description = "A layout composable that places its children in a vertical sequence.",
        backgroundColor = Color(0xFFDA7CF3)
    )
)

const val COMPOSE_QUADRANT_ROUTE = "compose_quadrant"

@Composable
fun ComposeQuadrant(
    data: List<QuadrantItem> = quadrants
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var i = 0
        val constraint = if(data.size % 2 == 0) data.size - 2 else data.size - 1
        while (i <= constraint) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                QuadrantItemCard(
                    title = data[i].title,
                    description = data[i].description,
                    backgroundColor = data[i].backgroundColor,
                    modifier = Modifier.weight(1f)
                )
                if ((i + 1) < data.size) {
                    QuadrantItemCard(
                        title = data[i + 1].title,
                        description = data[i + 1].description,
                        backgroundColor = data[i + 1].backgroundColor,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            i += 2
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