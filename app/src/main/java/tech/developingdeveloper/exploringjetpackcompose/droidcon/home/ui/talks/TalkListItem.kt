package tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import tech.developingdeveloper.exploringjetpackcompose.droidcon.core.ui.DroidconTheme
import tech.developingdeveloper.exploringjetpackcompose.droidcon.core.utils.DateTimeUtils
import tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks.model.Session
import tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks.model.TalkSlot

@Composable
fun TalkListItem(
    talkSlot: TalkSlot,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = talkSlot.formattedTime,
            modifier = Modifier
                .weight(0.3f)
                .padding(8.dp),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Sessions(talkSlot.sessions, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun Sessions(
    sessions: List<Session>,
    modifier: Modifier = Modifier,
) {
    FlowColumn(modifier = modifier) {
        sessions.forEachIndexed { index, session ->
            Session(session)

            if (index != sessions.lastIndex) {
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Composable
private fun Session(
    session: Session,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isSystemInDarkTheme()) {
                    Color.DarkGray
                } else {
                    Color.LightGray
                },
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = session.title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.body2,
        )

        Text(text = "in ${session.location}", style = MaterialTheme.typography.caption)

        Text(
            text = session.speaker ?: "",
            style = MaterialTheme.typography.body2,
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
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun TalkListItemPreview() {

    val talkSlot = TalkSlot(
        at = DateTimeUtils.getDate(2023, Calendar.SEPTEMBER, 14),
        sessions = listOf(
            Session(
                title = "Registration & Check-in",
                speaker = null,
                location = "Paris Room",
            ),
            Session(
                title = "Modern Android Development with Jetpack Compose",
                speaker = "Abhishek Saxena",
                location = "Paris Room",
            ),
        )
    )

    DroidconTheme {
        Surface {
            Column {
                TalkListItem(talkSlot)
            }
        }
    }
}

