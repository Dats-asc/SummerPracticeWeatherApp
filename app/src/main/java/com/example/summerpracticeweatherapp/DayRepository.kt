package com.example.summerpracticeweatherapp

import android.media.Image
import android.util.Log
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.network.models.weather.Main
import java.util.Calendar


object DayRepository {
    private var forecast: Main? = null
    private var calendar = Calendar.getInstance()
    var startDayIndex = 0
    var list: List<Day>? = listOf()
    fun calcAll() {
        list = (1..4).map { _ ->
            val day = Day(
                date = calendar.time.toString().split(" ").take(3).joinToString(" "),
                description = calcDesc(),
                minTemp = calcMinTemp(),
                maxTemp = calcMaxTemp(),
                icon = calcIcon()
            )
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            startDayIndex += 8
            day
        }
        startDayIndex = findStartDay()
    }
    val minTempArray = mutableListOf<Double>()
    val maxTempArray = mutableListOf<Double>()
    fun calcGraph() {
        minTempArray.clear()
        maxTempArray.clear()
        for (i in 0..3){
            minTempArray.add(calcMinTemp())
            maxTempArray.add(calcMaxTemp())
            startDayIndex += 8
        }
        startDayIndex = findStartDay()
    }


    fun loadData(weather: Main) {
        forecast = weather
        startDayIndex = findStartDay()
        calendar = Calendar.getInstance()
        calcAll()
    }

    fun findStartDay(): Int {
        var k = 0
        while (forecast?.list?.get(k)!!.dt % 86400L != 0L) {
            k += 1
        }
        return k
    }

    fun calcDesc(): String {
        val dictionary = mutableMapOf<String, Int>()
        val words = mutableListOf<String>()
        for (i in startDayIndex..startDayIndex + 7) {
            words.add(forecast?.list?.get(i)!!.weather[0].description)
        }
        for (word in words) {
            if (dictionary.containsKey(word)) {
                val count = dictionary[word]!!
                dictionary[word] = count + 1
            } else {
                dictionary[word] = 1
            }
        }
        return dictionary.maxByOrNull { it.value }?.key.toString()
    }

    fun calcMaxTemp(): Double {
        val array = mutableListOf<Double>()
        for (i in startDayIndex..startDayIndex + 7) {
            array.add(forecast?.list?.get(i)!!.main.tempMax)
        }
        return array.maxOrNull()!!
    }

    fun calcMinTemp(): Double {
        val array = mutableListOf<Double>()
        for (i in startDayIndex..startDayIndex + 7) {
            array.add(forecast?.list?.get(i)!!.main.tempMin)
        }
        return array.minOrNull()!!
    }
    fun calcIcon(): String{
        val dictionary = mutableMapOf<String, Int>()
        val words = mutableListOf<String>()
        for (i in startDayIndex..startDayIndex + 7) {
            words.add(forecast?.list?.get(i)!!.weather[0].icon)
        }
        for (word in words) {
            if (dictionary.containsKey(word)) {
                val count = dictionary[word]!!
                dictionary[word] = count + 1
            } else {
                dictionary[word] = 1
            }
        }
        return dictionary.maxByOrNull { it.value }?.key.toString()
    }
}