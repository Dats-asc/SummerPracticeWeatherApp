package com.example.summerpracticeweatherapp.network

import com.example.summerpracticeweatherapp.network.models.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather?appid=5801e204d69957dd4fbfa53b3510fad9")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
    ): WeatherResponse

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String
    ): WeatherResponse
}