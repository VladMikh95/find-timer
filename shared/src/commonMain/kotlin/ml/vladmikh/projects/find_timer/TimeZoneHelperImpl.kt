package ml.vladmikh.projects.find_timer

import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class TimeZoneHelperImpl: TimeZoneHelper {
    override fun getTimeZoneStrings(): List<String> {
        return TimeZone.availableZoneIds.sorted()
    }

    override fun currentTime(): String {
        val currentMoment: Instant = Clock.System.now()
        // 2
        val dateTime: LocalDateTime =
            currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        // 3
        return formatDateTime(dateTime)
    }

    override fun currentTimeZone(): String {
        val currentTimeZone = TimeZone.currentSystemDefault()
        return currentTimeZone.toString()
    }

    override fun hoursFromTimeZone(otherTimeZoneId: String): Double {

        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentUTCInstant: Instant = Clock.System.now()
        // Date time in other timezone
        val otherTimeZone = TimeZone.of(otherTimeZoneId)
        val currentDateTime: LocalDateTime =
            currentUTCInstant.toLocalDateTime(currentTimeZone)
        val currentOtherDateTime: LocalDateTime =
            currentUTCInstant.toLocalDateTime(otherTimeZone)

        return abs(
            (currentDateTime.hour -
                    currentOtherDateTime.hour) * 1.0
        )
    }

    override fun getTime(timezoneId: String): String {

        val timezone = TimeZone.of(timezoneId)
        val currentMoment: Instant = Clock.System.now()
        val dateTime: LocalDateTime = currentMoment.toLocalDateTime(timezone)

        return formatDateTime(dateTime)
    }

    override fun getDate(timezoneId: String): String {
        val timezone = TimeZone.of(timezoneId)
        val currentMoment: Instant = Clock.System.now()
        val dateTime: LocalDateTime =
            currentMoment.toLocalDateTime(timezone)
        // 1
        return "${dateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }}, " +
                "${
                    dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }
                } ${dateTime.date.dayOfMonth}"
    }

    override fun search(startHour: Int, endHour: Int, timezoneStrings: List<String>): List<Int> {

        //list to return all the valid hours
        val goodHours = mutableListOf<Int>()
        // time range from start to end hours
        val timeRange = IntRange(max(0, startHour), min(23, endHour))
        // the current time zone
        val currentTimeZone = TimeZone.currentSystemDefault()
        //  Go through each hour in the time range
        for (hour in timeRange) {
            var isGoodHour = false
            // Go through each time zone in the time zone list
            for (zone in timezoneStrings) {
                val timezone = TimeZone.of(zone)
                //  If it’s the same time zone as the current one, then you know it’s good.
                if (timezone == currentTimeZone) {
                    continue
                }
                //  Check if the hour is valid
                if (!isValid(
                        timeRange = timeRange,
                        hour = hour,
                        currentTimeZone = currentTimeZone,
                        otherTimeZone = timezone
                    )
                ) {
                    Napier.d("Hour $hour is not valid for time range")
                    isGoodHour = false
                    break
                } else {
                    Napier.d("Hour $hour is Valid for time range")
                    isGoodHour = true
                }
            }
            //  If, after going through every hour and it’s a good hour, add it to our list.
            if (isGoodHour) {
                goodHours.add(hour)
            }
        }
        //  Return the list of hours
        return goodHours
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        val stringBuilder = StringBuilder()
        val minute = dateTime.minute
        var hour = dateTime.hour % 12
        if (hour == 0) hour = 12
        val amPm = if (dateTime.hour < 12) " am" else " pm"
        stringBuilder.append(hour.toString())
        stringBuilder.append(":")
        if (minute < 10) {
            stringBuilder.append('0')
        }
        stringBuilder.append(minute.toString())
        stringBuilder.append(amPm)
        return stringBuilder.toString()
    }

    private fun isValid(
        timeRange: IntRange,
        hour: Int,
        currentTimeZone: TimeZone,
        otherTimeZone: TimeZone
    ): Boolean {

        // verify if the hour is in the time range
        if (hour !in timeRange) {
            return false
        }

        val currentUTCInstant: Instant = Clock.System.now()
        val currentOtherDateTime: LocalDateTime =
            currentUTCInstant.toLocalDateTime(otherTimeZone)
        val otherDateTimeWithHour = LocalDateTime(
            currentOtherDateTime.year,
            currentOtherDateTime.monthNumber,
            currentOtherDateTime.dayOfMonth,
            hour,
            0,
            0,
            0
        )
        val localInstant =
            otherDateTimeWithHour.toInstant(currentTimeZone)
        val convertedTime = localInstant.toLocalDateTime(otherTimeZone)
        Napier.d("Hour $hour in Time Range ${otherTimeZone.id} is $ {convertedTime.hour}")

        return convertedTime.hour in timeRange

    }
}
