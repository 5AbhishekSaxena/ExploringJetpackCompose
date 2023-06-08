package tech.developingdeveloper.exploringjetpackcompose.animations

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.developingdeveloper.exploringjetpackcompose.R
import tech.developingdeveloper.exploringjetpackcompose.ui.theme.ExploringJetpackComposeTheme

@Composable
fun Passport() {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "PASSPORT",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.height(IntrinsicSize.Max)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sample_passport_photo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp)),
                )

                Spacer(modifier = Modifier.width(24.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    LabelAndValue(label = "SURNAME", value = "DOE")

                    Spacer(modifier = Modifier.height(16.dp))

                    LabelAndValue(label = "FIRSTNAME", value = "JOHN")

                    Spacer(modifier = Modifier.height(16.dp))

                    LabelAndValue(label = "NATIONALITY", value = "INDIAN")

                    Spacer(modifier = Modifier.height(16.dp))

                    LabelAndValue(label = "DATE OF ISSUE", value = "21 JUNE 2019")
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    LabelAndValue(label = "", value = "")

                    Spacer(modifier = Modifier.height(16.dp))

                    LabelAndValue(label = "CARD NUMBER", value = "IN0453F563")

                    Spacer(modifier = Modifier.height(16.dp))

                    LabelAndValue(label = "DATE OF BIRTH", value = "12 APRIL 1999")

                    Spacer(modifier = Modifier.height(16.dp))

                    LabelAndValue(label = "EXPIRATION", value = "20 JUNE 2024")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            FooterText(text = "PIN0453F563<<<<DOE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

            Spacer(modifier = Modifier.height(4.dp))

            FooterText(text = "599IR345<<<<JOHN<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        }
    }
}

@Composable
private fun FooterText(text: String) {
    Text(
        text = text,
        maxLines = 1,
        color = Color(0xFF6B6B6B),
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.0.sp,
    )
}

@Composable
private fun LabelAndValue(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = Color(0xFF6B6B6B),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
        )

        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
        )
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
private fun PassportPreview() {
    ExploringJetpackComposeTheme {
        Surface {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                Passport()
            }
        }
    }
}