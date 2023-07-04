package com.example.summerpracticeweatherapp.network

import retrofit2.http.GET

interface WeatherService {

    @GET("/weather")
    suspend fun getWeather()
}