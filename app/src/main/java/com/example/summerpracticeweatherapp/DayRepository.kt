package com.example.summerpracticeweatherapp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar

object DayRepository {
    private val calendar = Calendar.getInstance()

    val list: List<Day> = (0..14).map { index ->
        val date = calendar.time.toString().split(" ").take(3).joinToString(" ")
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        Day(status = "*statusWeather*", name = date)
    }
}