package tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks.model

import java.util.Date
import tech.developingdeveloper.exploringjetpackcompose.droidcon.core.utils.DateTimeUtils

data class TalkSlot(
    val at: Date,
    val sessions: List<Session>
) {
    val formattedTime: String = DateTimeUtils.formattedTimeForUI(at)
}
