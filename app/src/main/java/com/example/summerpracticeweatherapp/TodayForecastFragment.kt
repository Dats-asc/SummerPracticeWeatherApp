package com.example.summerpracticeweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding
import com.example.summerpracticeweatherapp.databinding.FragmentTodayForecastBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.network.models.weather.Main
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TodayForecastFragment : Fragment(R.layout.fragment_today_forecast) {
    var binding : FragmentTodayForecastBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTodayForecastBinding.bind(view)


    }

    fun updateUI(weather: Main){

    }

    override fun onStart() {
        super.onStart()
        this.lifecycleScope.launch(Dispatchers.Main) {
            Forecast.getForecast().collect { currentWeather ->
                currentWeather?.let {
                    updateUI(currentWeather)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}