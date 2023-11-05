package tech.developingdeveloper.exploringjetpackcompose.recipesapp

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.developingdeveloper.exploringjetpackcompose.R
import tech.developingdeveloper.exploringjetpackcompose.recipesapp.data.Recipe
import tech.developingdeveloper.exploringjetpackcompose.recipesapp.data.strawberryCake
import tech.developingdeveloper.exploringjetpackcompose.recipesapp.theme.DarkGray
import tech.developingdeveloper.exploringjetpackcompose.recipesapp.theme.Gray
import tech.developingdeveloper.exploringjetpackcompose.recipesapp.theme.LightGray
import tech.developingdeveloper.exploringjetpackcompose.recipesapp.theme.Pink
import tech.developingdeveloper.exploringjetpackcompose.recipesapp.theme.Shapes
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme
import kotlin.math.max
import kotlin.math.min


private val AppBarCollapsedHeight = 56.dp
private val AppBarExpendedHeight = 400.dp

@Composable
fun RecipesHomeContent() {
    val recipe = remember { strawberryCake }

    val scrollState = rememberLazyListState()

    Box {
        ParallaxToolbar(recipe = recipe, scrollState = scrollState)
        Content(recipe = recipe, scrollState = scrollState)
    }
}

@Composable
private fun ParallaxToolbar(
    recipe: Recipe,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
) {
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset = with(LocalDensity.current) { imageHeight.roundToPx() }

    val firstVisibleItemScrollOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val offSet = min(firstVisibleItemScrollOffset, maxOffset)
    val offsetInProgress = max(0f, offSet * 3f - 2f * maxOffset) / maxOffset
    TopAppBar(
        elevation = if (offSet == maxOffset) 4.dp else 0.dp,
        modifier = modifier
            .height(AppBarExpendedHeight)
            .offset { IntOffset(0, -offSet) },
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetInProgress
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.strawberry_pie_1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                0.4f to Color.Transparent,
                                1f to Color.White,
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp,
                        ),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        recipe.category,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .clip(Shapes.small)
                            .background(LightGray)
                            .padding(vertical = 6.dp, horizontal = 16.dp)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapsedHeight),
            ) {
                Text(
                    recipe.title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetInProgress).dp)
                        .scale(1 - 0.25f * offsetInProgress),
                )
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight)
            .padding(horizontal = 16.dp)
    ) {
        CircularButton(Icons.Default.ArrowBack)
        CircularButton(Icons.Default.FavoriteBorder)
    }
}

@Composable
fun CircularButton(
    imageVector: ImageVector,
    contentColor: Color = Gray,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = contentColor,
        ),
        elevation = elevation,
        modifier = Modifier.size(38.dp),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
        )
    }
}

@Composable
private fun Content(
    recipe: Recipe,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpendedHeight),
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            BasicInfo(recipe)
            Description(recipe)
            ServingCalculator()
            IngredientsHeader()
            IngredientsList(recipe)
            ShoppingListButton()
            Reviews(recipe)
            Images()
        }
    }
}

@Composable
private fun BasicInfo(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(R.drawable.ic_clock, recipe.cookingTime)
        InfoColumn(R.drawable.ic_flame, recipe.energy)
        InfoColumn(R.drawable.ic_star, recipe.rating)
    }
}


@Composable
private fun Description(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Text(
        text = recipe.description,
        fontWeight = FontWeight.Medium,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    )
}

@Composable
private fun ServingCalculator(modifier: Modifier = Modifier) {
    var value by remember { mutableIntStateOf(6) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(Shapes.medium)
            .background(LightGray)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Serving",
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.weight(1f))
        CircularButton(
            imageVector = Icons.Default.Remove,
            elevation = null,
            contentColor = Pink,
            onClick = { value-- }
        )
        Text(
            text = value.toString(),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(16.dp),
        )
        CircularButton(
            imageVector = Icons.Default.Add,
            elevation = null,
            contentColor = Pink,
            onClick = { value++ }
        )
    }
}

@Composable
private fun IngredientsHeader(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(16.dp)
            .clip(Shapes.medium)
            .background(LightGray),
    ) {
        TabButton(text = "Ingredients", active = true, modifier = Modifier.weight(1f))
        TabButton(text = "Tools", active = false, modifier = Modifier.weight(1f))
        TabButton(text = "Steps", active = false, modifier = Modifier.weight(1f))

    }
}


@Composable
private fun IngredientsList(recipe: Recipe, modifier: Modifier = Modifier) {
    Column {
        Text("Items: ${recipe.ingredients.size}")
        EasyGrid(
            numberOfColumns = 3,
            items = recipe.ingredients,
            modifier = modifier,
        ) {
            IngredientCard(
                title = it.title,
                subtitle = it.subtitle,
                imageRes = it.image,
            )
        }
    }
}

@Composable
private fun <T> EasyGrid(
    numberOfColumns: Int,
    items: List<T>,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        for (i in items.indices step numberOfColumns) {
            Row {
                for (j in 0 until numberOfColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun IngredientCard(
    title: String,
    subtitle: String,
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(bottom = 16.dp)
    ) {
        Card(
            shape = Shapes.large,
            colors = CardDefaults.cardColors(containerColor = LightGray),
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        Text(
            text = title,
//            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = subtitle,
//            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            color = DarkGray,
        )
    }
}

@Composable
private fun ShoppingListButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        elevation = null,
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightGray,
            contentColor = Color.Black,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Add to shopping list.",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun Reviews(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Column {
            Text(text = "Reviews", fontWeight = FontWeight.Bold)
            Text(recipe.reviews, color = DarkGray)
        }

        Button(
            onClick = {},
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Pink
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("See All")
                Icon(
                    imageVector = Icons.Default.ArrowRight,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun Images(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_2),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(Shapes.small)
        )

        Spacer(Modifier.weight(0.1f))

        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_3),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(Shapes.small)
        )

    }
}

@Composable
private fun TabButton(
    text: String,
    active: Boolean,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {},
        shape = Shapes.medium,
        colors = if (active) {
            ButtonDefaults.buttonColors(
                containerColor = Pink,
                contentColor = Color.White,
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = LightGray,
                contentColor = DarkGray,
            )
        },
        elevation = null,
        modifier = modifier.fillMaxHeight(),
    ) {
        Text(text)
    }
}

@Composable
private fun InfoColumn(
    @DrawableRes iconRes: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Pink,
            modifier = Modifier.size(24.dp)
        )

        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

//
//@Preview(
//    name = "Night Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    heightDp = 2000,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun RecipesHomeContentPreview() {
    ExploringJetpackComposeTheme {
        Surface {
            RecipesHomeContent()
        }
    }
}