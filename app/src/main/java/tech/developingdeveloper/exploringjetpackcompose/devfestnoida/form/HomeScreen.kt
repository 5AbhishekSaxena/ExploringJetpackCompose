package tech.developingdeveloper.exploringjetpackcompose.devfestnoida.form

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.R
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

private val bgColor = Color.LightGray.copy(alpha = 0.4f)

@Composable
fun HomeScreen(viewState: HomeViewState) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Header(viewState)

        Carousel(viewState)

        Headings(viewState)
    }
}

@Composable
private fun Header(viewState: HomeViewState) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Salutation(viewState)

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.background(bgColor, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.Gray,
            )
        }
    }
}

@Composable
private fun Salutation(
    homeViewState: HomeViewState,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painterResource(id = R.drawable.profile_pic),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Hi ${homeViewState.name}",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun Carousel(
    viewState: HomeViewState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .background(bgColor, RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(8.dp)
                        .background(
                            if (it == 0) MaterialTheme.colorScheme.primary else bgColor,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
private fun Headings(homeViewState: HomeViewState) {
    Text(
        text = "Heading",
        style = MaterialTheme.typography.titleLarge
    )

    repeat(10) {
        ElevatedCard {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .background(bgColor, CircleShape)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .width(72.dp)
                            .background(bgColor, RoundedCornerShape(4.dp))
                    )

                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .width(120.dp)
                            .background(bgColor, RoundedCornerShape(4.dp))
                    )
                }
            }
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
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun HomeScreenPreview() {
    val homeViewState = HomeViewState(name = "John", emptyList(), emptyList())
    ExploringJetpackComposeTheme {
        Surface {
            Box(modifier = Modifier.padding(16.dp)) {
                HomeScreen(homeViewState)
            }
        }
    }
}

data class HomeViewState(
    val name: String,
    val images: List<String>,
    val headings: List<String>,
)