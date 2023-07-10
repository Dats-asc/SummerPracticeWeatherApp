package com.example.summerpracticeweatherapp.network.models.weather.city

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("name")
    val name: String,
    @SerialName("country")
    val country: String
)
