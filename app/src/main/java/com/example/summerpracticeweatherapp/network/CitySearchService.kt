package com.example.summerpracticeweatherapp.network

import com.example.summerpracticeweatherapp.network.models.weather.city.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CitySearchService {

    @Headers("X-Api-Key: hGjMsmSQabwE3Kt4jc8nYw==gvyUtwyEbyKSOY1B")
    @GET("city?limit=20")
    suspend fun getCitiesByRequest(
        @Query("name") name: String
    ): List<SearchResponse>
}