package com.example.summerpracticeweatherapp.network

import com.example.summerpracticeweatherapp.network.models.weather.Main
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String
    ): Main
}