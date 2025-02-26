package ml.vladmikh.projects.find_timer.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ml.vladmikh.projects.find_timer.TimeZoneHelper
import ml.vladmikh.projects.find_timer.TimeZoneHelperImpl

const val timePeriod = 60000L

@Composable
fun TimeZoneScreen(
    currentTimezoneStrings: SnapshotStateList<String>
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

        LazyColumn(state = listTimeZones) {

            items(currentTimezoneStrings.size,

                //key fild for unique key for each timeZoneCard
                key = { timezone -> timezone }
            ) { index ->

                val timezoneString = currentTimezoneStrings[index]


                AnimatedSwipeDismiss(
                    modifier = Modifier,
                    item = timezoneString,
                    background = { _ ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(50.dp)
                                .background(Color.White)
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp
                                )
                        ) {
                            val alpha = 1f
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),
                                tint = Color.Red.copy(alpha = alpha)
                            )
                        }
                    },
                    content = {
                        TimeCard(
                            timeZone = timezoneString,
                            hours =
                            timeZoneHelper.hoursFromTimeZone(timezoneString),
                            time =
                            timeZoneHelper.getTime(timezoneString),
                            date =
                            timeZoneHelper.getDate(timezoneString)
                        )
                    },
                    onDismiss = { zone ->
                        if (currentTimezoneStrings.contains(zone)) {
                            currentTimezoneStrings.remove(zone)
                        }
                    }
                )
            }
        }

    }
}