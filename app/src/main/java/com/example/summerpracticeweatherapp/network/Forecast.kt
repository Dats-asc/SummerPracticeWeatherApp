package com.example.summerpracticeweatherapp.network

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.summerpracticeweatherapp.network.models.weather.Main
import com.example.summerpracticeweatherapp.utils.SharedPrefsUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Forecast {
    private var forecast: MutableStateFlow<Main?> = MutableStateFlow(null)


    private val coroutineScope = CoroutineScope(SupervisorJob())
    private val weatherService by lazy {
        NetworkManager.getWeatherService()
    }

    fun init(ctx: Context){
        coroutineScope.launch(Dispatchers.IO) {
            val currentWeather = weatherService.getWeatherByCity(SharedPrefsUtils.getSavedCity(ctx))
            withContext(Dispatchers.Main) {
                forecast.emit(currentWeather)
            }
        }
    }

    fun clear(){
        coroutineScope.cancel()
    }

    fun getForecast() = forecast

    fun updateForecast(ctx: Context, city: String) {

        coroutineScope.launch(Dispatchers.IO) {
            SharedPrefsUtils.saveCity(ctx, city)
            val currentWeather = weatherService.getWeatherByCity(city)
            withContext(Dispatchers.Main) {
                forecast.emit(currentWeather)
            }
        }
    }


}