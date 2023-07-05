package com.example.summerpracticeweatherapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit

object NetworkManager {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "5801e204d69957dd4fbfa53b3510fad9"

    private var weatherService: WeatherService? = null

    private var apiInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url.newBuilder().apply {
            addQueryParameter("appid", API_KEY)
            addQueryParameter("lang", "ru")
            addQueryParameter("units", "metric")
        }.build()
        chain.proceed(chain.request().newBuilder().url(newUrl).build())
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        val kotlinxConverterFactory = Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(contentType)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(kotlinxConverterFactory)
            .build()
    }

    fun getWeatherService(): WeatherService {
        if (weatherService == null) {
            weatherService = getRetrofit().create(WeatherService::class.java)
        }
        return weatherService!!
    }
}