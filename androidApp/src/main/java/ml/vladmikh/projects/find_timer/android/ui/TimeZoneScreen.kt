package ml.vladmikh.projects.find_timer.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ml.vladmikh.projects.find_timer.TimeZoneHelper
import ml.vladmikh.projects.find_timer.TimeZoneHelperImpl

const val timePeriod = 60000L

@Composable
fun TimeZoneScreen(
    currentTimezoneString: SnapshotStateList<String>
) {

    val timeZoneHelper: TimeZoneHelper = TimeZoneHelperImpl()
    val listTimeZones = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {

        var time by remember { mutableStateOf(timeZoneHelper.currentTime()) }

        LaunchedEffect(Unit) {

            while (true) {
                time = timeZoneHelper.currentTime()
                delay(timePeriod)
            }
        }

        LocalTimeCard(
            city = timeZoneHelper.currentTimeZone(),
            time = time,
            date = timeZoneHelper.getDate(timeZoneHelper.currentTimeZone())
        )

        Spacer(modifier = Modifier.size(16.dp))
    }
}