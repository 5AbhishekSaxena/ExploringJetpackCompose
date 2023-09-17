package tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.developingdeveloper.exploringjetpackcompose.droidcon.home.ui.talks.model.TalkSlot

@Composable
fun Talks(talkSlots: List<TalkSlot>, modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(talkSlots) {
            TalkListItem(talkSlot = it)
        }
    }
}