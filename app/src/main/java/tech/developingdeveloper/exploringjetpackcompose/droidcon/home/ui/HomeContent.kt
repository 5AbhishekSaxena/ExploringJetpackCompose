package tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar
import tech.developingdeveloper.exploringjetpackcompose.droidcon.core.ui.DroidconTheme
import tech.developingdeveloper.exploringjetpackcompose.droidcon.core.utils.DateTimeUtils
import tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks.Talks
import tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks.model.Session
import tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks.model.TalkSlot

@Composable
fun HomeContent(
    viewState: HomeViewState,
) {
    Scaffold(
        topBar = {
            DroidconTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Talks(
                talkSlots = viewState.talks,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun DroidconTopAppBar() {
    TopAppBar {
        Text(text = "Droidcon NYC 2023")
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
private fun HomeContentPreview() {

    val talkSlots = listOf(
        TalkSlot(
            at = DateTimeUtils.getDate(
                year = 2023,
                month = Calendar.SEPTEMBER,
                dayOfMonth = 14,
                hourOfDay = 7,
                minute = 30,
            ),
            sessions = listOf(
                Session(
                    "Registration & Check-in",
                    null,
                    "Paris Room"
                ),
            ),
        ),
        TalkSlot(
            at = DateTimeUtils.getDate(
                year = 2023,
                month = Calendar.SEPTEMBER,
                dayOfMonth = 14,
                hourOfDay = 9,
                minute = 0,
            ),
            sessions = listOf(
                Session(
                    "Modern Android Development",
                    "Abhishek Saxena",
                    "Paris Room"
                ),
            ),
        ),
        TalkSlot(
            at = DateTimeUtils.getDate(
                year = 2023,
                month = Calendar.SEPTEMBER,
                dayOfMonth = 14,
                hourOfDay = 10,
                minute = 20,
            ),
            sessions = listOf(
                Session(
                    "Composing Creatively with Custom Layouts",
                    "Huyen Tue Dao",
                    "Paris Room"
                ),
                Session(
                    "Interception",
                    "Sam Edwards",
                    "Dubai Room"
                ),
                Session(
                    "The Art of KMP: what you need to know as a multiplatform developer",
                    "Lena Stepanova",
                    "Tokyo Room"
                ),
            ),
        ),
    )

    val viewState = HomeViewState(talkSlots)

    DroidconTheme {
        Surface {
            HomeContent(viewState)
        }
    }
}