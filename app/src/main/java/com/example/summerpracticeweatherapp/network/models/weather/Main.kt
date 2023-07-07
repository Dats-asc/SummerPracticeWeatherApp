package com.example.summerpracticeweatherapp.network.models.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("temp")
    val temp: Float,
    @SerialName("temp_min")
    val temp_min: Float,
    @SerialName("temp_max")
    val temp_max: Float,
    @SerialName("feels_like")
    val feels_like: Float
)
