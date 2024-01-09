package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.simplified

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LemonadeRepository
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.LemonadeUiItem
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.DefaultOnClickStrategy
import tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade.click_strategy.RandomMultiClickStrategy
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun LemonadeAddSimplified() {
    var currentStep by remember { mutableIntStateOf(0) }

    var items by remember { mutableStateOf(emptyList<LemonadeUiItem>()) }

    val defaultOnClickStrategy = remember { DefaultOnClickStrategy(runnable = { currentStep++ }) }

    val randomClickStrategy = remember { RandomMultiClickStrategy(runnable = { currentStep++ }) }

    val lemonade = items.getOrNull(currentStep)

    LaunchedEffect(key1 = Unit) {
        items = LemonadeRepository().getLemons()
            .map {
                LemonadeUiItem(
                    title = it.title,
                    imageRes = it.imageUrl,
                    contentDescription = it.contentDescription,
                    onClickStrategy = when (it.clickStrategy) {
                        "random" -> randomClickStrategy
                        else -> defaultOnClickStrategy
                    }
                )
            }
    }

    LaunchedEffect(key1 = currentStep) {
        if (currentStep == items.size)
            currentStep = 0
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            if (lemonade != null) {
                LemonTextAndImage(
                    title = lemonade.title,
                    drawableResourceId = lemonade.imageRes,
                    contentDescription = lemonade.contentDescription,
                    onImageClick = lemonade.onClickStrategy::onClick,
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    title: String,
    drawableResourceId: Int,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier,
    clicks: Int = 1,
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .size(128.dp)
                        .padding(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun LemonadeAddSimplifiedPreview() {
    ExploringJetpackComposeTheme {
        Surface {
            LemonadeAddSimplified()
        }
    }
}