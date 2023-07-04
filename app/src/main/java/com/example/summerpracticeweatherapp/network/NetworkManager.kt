package com.example.summerpracticeweatherapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object NetworkManager {

    private const val BASE_URL = "https://api.openweathermap.org/data/3.0"

    private var weatherService: WeatherService? = null

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .build()
    }

    fun getWeatherService(): WeatherService {
        if (weatherService == null) {
            weatherService = getRetrofit().create(WeatherService::class.java)
        }
        return weatherService!!
    }
}