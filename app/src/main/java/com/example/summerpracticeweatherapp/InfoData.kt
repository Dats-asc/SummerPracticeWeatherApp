package com.example.summerpracticeweatherapp

import com.example.summerpracticeweatherapp.utils.InfoPage

object InfoData {
    val info = listOf<InfoPage>(
        InfoPage("Weather Wise", "Наше приложение предоставляет точный прогноз погоды с помощью API OpenWeather. Получите подробную информацию о погоде в вашем местоположении или любом другом городе мира. При вводе в поиске вы увидите подсказки для заполнения"),
        InfoPage("Functionality", "Detailed weather forecast for today with temperature, precipitation, wind and more.\n\n" +
                "Weather forecast for 4 days ahead with main indicators\n\n" +
                "Temperature change graph for 4 days"),

    )
}