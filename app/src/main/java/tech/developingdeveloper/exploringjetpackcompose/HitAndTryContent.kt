package tech.developingdeveloper.exploringjetpackcompose

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.PoppingFontFamily
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.Shapes

const val HIT_AND_TRY_CONTENT_ROUTE = "hit_and_try_content"

@Composable
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
fun HitAndTryContent() {
//    ExpandableCardList()
    AgreementContent()
}

@Composable
@ExperimentalMaterialApi
@ExperimentalAnimationApi
private fun ExpandableCardList() {
    var expandedIndex by remember { mutableStateOf(-1) }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(10) { index ->
            ExpandableCard(
                expanded = index == expandedIndex,
                onClick = {
                    expandedIndex =
                        if (expandedIndex == index) -1
                        else index
                }
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun ExpandableCard(
    expanded: Boolean,
    onClick: () -> Unit,
) {

    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = Shapes.medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My title",
                    modifier = Modifier
                        .weight(1f),
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = onClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Drop down arrow",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }

            AnimatedVisibility(
                visible = expanded,
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                            "when an unknown printer took a galley of type and scrambled it to make a type " +
                            "specimen book. It has survived not only five centuries, but also the leap into " +
                            "electronic typesetting, remaining essentially unchanged. It was popularised in " +
                            "the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, " +
                            "and more recently with desktop publishing software like Aldus PageMaker " +
                            "including versions of Lorem Ipsum.",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
@ExperimentalComposeUiApi
fun AgreementContent() {

    var consentGranted by remember { mutableStateOf(false) }

    Column {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            TitleText()

            Spacer(modifier = Modifier.height(24.dp))

            TermsAndConditionsText(
                modifier = Modifier.Companion
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            )

            Spacer(modifier = Modifier.height(24.dp))

            TermsAndConditionsAcceptanceCheckbox(
                checked = consentGranted,
                onCheckedChange = {
                    consentGranted = it
                }
            )
        }

        AcceptanceButtonRow(
            consentGranted = consentGranted,
            onAcceptButtonClick = {},
            onDenyButtonClick = {}
        )
    }
}

@Composable
private fun TitleText() {
    Text(
        text = "Terms of use, warranties and release agreement".uppercase(),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.h6.fontSize,
        fontFamily = PoppingFontFamily
    )
}

@Composable
private fun TermsAndConditionsText(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                    "when an unknown printer took a galley of type and scrambled it to make a type " +
                    "specimen book. It has survived not only five centuries, but also the leap into " +
                    "electronic typesetting, remaining essentially unchanged. It was popularised in " +
                    "the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, " +
                    "and more recently with desktop publishing software like Aldus PageMaker " +
                    "including versions of Lorem Ipsum.",
            fontFamily = PoppingFontFamily,
            color = Color.Gray
        )
    }
}

@Composable
private fun TermsAndConditionsAcceptanceCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onCheckedChange(!checked) })
            .padding(4.dp)
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "I have read the above Terms and Conditions")
    }
}

@Composable
@ExperimentalComposeUiApi
fun AcceptanceButtonRow(
    consentGranted: Boolean,
    onAcceptButtonClick: () -> Unit,
    onDenyButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        TextButton(
            onClick = onAcceptButtonClick,
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color(0xFFADD8E6),
                contentColor = Color.Black
            ),
            enabled = consentGranted
        ) {
            Text(text = "Accept".uppercase())
        }

        TextButton(
            onClick = onDenyButtonClick,
            contentPadding = PaddingValues(24.dp),
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color(0xFFE7E8E9),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Deny".uppercase())
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)

@Composable
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Suppress("UnusedPrivateMember")
private fun StevdzaSanContentPreview() {
    ExploringJetpackComposeTheme {
        Surface {
            HitAndTryContent()
        }
    }
}