package com.example.summerpracticeweatherapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.summerpracticeweatherapp.DayRepository.list
import com.example.summerpracticeweatherapp.DayRepository.loadData
import com.example.summerpracticeweatherapp.databinding.FragmentDaysBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.network.NetworkManager
import com.example.summerpracticeweatherapp.network.models.weather.Main
import com.example.summerpracticeweatherapp.utils.loadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DaysFragment : Fragment(R.layout.fragment_days) {
    private var binding: FragmentDaysBinding? = null
    private var adapter: DayAdapter? = null

    private var savedCity: String? = null

    private val weatherService by lazy {
        NetworkManager.getWeatherService()
    }

    private val coroutineScope = CoroutineScope(SupervisorJob())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDaysBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    private fun updateUI(weather: Main) {
        loadData(weather)
        binding?.tvCity?.text = weather.city.name
        binding?.tvTemp?.text = weather.list?.get(0)?.main?.temp.toString() + "°C"
        initAdapter()
        binding?.ivIcon?.loadImage("http://openweathermap.org/img/w/${weather.list[0].weather[0].icon}.png")
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

    private fun initAdapter() {
        adapter = list?.let { DayAdapter(it) }
        binding?.rvDay?.adapter = adapter
    }
}
