package tech.developingdeveloper.exploringjetpackcompose.composebasicscodelab.unit_two.lemonade

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.developingdeveloper.exploringjetpackcompose.R
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun LemonadeAppContent(lemonadeUiItem: LemonadeUiItem) {
    LemonadeCard(lemonadeUiItem)
}

@Composable
private fun LemonadeCard(lemonadeUiItem: LemonadeUiItem) {
    Column(
        modifier = Modifier
            .clickable(onClick = {
                lemonadeUiItem.onClickStrategy.onClick()
            })
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = lemonadeUiItem.title,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = lemonadeUiItem.imageRes),
            contentDescription = lemonadeUiItem.contentDescription,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(105, 205, 216),
                    shape = RoundedCornerShape(8.dp)
                )
        )
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
private fun LemonadeAppContentPreview() {
    ExploringJetpackComposeTheme {

        val lemonadeItem = LemonadeUiItem(
            title = stringResource(id = R.string.tap_the_lemon_tree_to_select_a_lemon),
            imageRes = R.drawable.lemon_tree,
            contentDescription = stringResource(id = R.string.lemon_tree)
        )

        LemonadeAppContent(lemonadeUiItem = lemonadeItem)
    }
}