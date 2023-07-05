package com.example.summerpracticeweatherapp.network.models.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("main")
    val main: Main
)