package tech.developingdeveloper.exploringjetpackcompose.droidcon.core.utils

import java.util.Calendar
import java.util.Date

object DateTimeUtils {

    fun getDate(year: Int, month: Int, dayOfMonth: Int): Date {
        return getDate(
            year = year,
            month = month,
            dayOfMonth = dayOfMonth,
            hourOfDay = 0,
            minute = 0,
            second = 0
        )
    }

    fun getDate(
        year: Int,
        month: Int,
        dayOfMonth: Int,
        hourOfDay: Int,
        minute: Int,
        second: Int
    ): Date {
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, second)
        }.time
    }

    fun getDay(date: Date): Int {
        return date.getCalendar { it.get(Calendar.DAY_OF_MONTH) }
    }

    fun getMonth(date: Date): Int {
        return date.getCalendar { it.get(Calendar.MONTH) }
    }

    fun getYear(date: Date): Int {
        return date.getCalendar { it.get(Calendar.YEAR) }
    }

    fun getHour(date: Date): Int {
        return date.getCalendar { it.get(Calendar.HOUR_OF_DAY) }
    }

    fun getMinutes(date: Date): Int {
        return date.getCalendar { it.get(Calendar.MINUTE) }
    }

    private inline fun <R> Date.getCalendar(block: (Calendar) -> R): R {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return block(calendar)
    }

    fun formattedTimeForUI(at: Date): String {
            val hour = getHour(at).applyPaddingIfRequired()
            val minutes = getMinutes(at).applyPaddingIfRequired()
            return "$hour:$minutes"
        }

    private fun Int.applyPaddingIfRequired(): String {
        return String.format("%02d", this)
    }
}
