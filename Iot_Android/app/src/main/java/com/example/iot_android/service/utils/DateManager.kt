package com.example.iot_android.service.utils

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*

object DateManager {
    fun getDate(date: LocalDateTime) : String {
        return when(date.dayOfWeek) {
            DayOfWeek.FRIDAY -> "금요일"
            DayOfWeek.MONDAY -> "월요일"
            DayOfWeek.SATURDAY -> "토요일"
            DayOfWeek.SUNDAY -> "일요일"
            DayOfWeek.THURSDAY -> "목요일"
            DayOfWeek.TUESDAY -> "화요일"
            DayOfWeek.WEDNESDAY -> "수요일"
        }
    }
}