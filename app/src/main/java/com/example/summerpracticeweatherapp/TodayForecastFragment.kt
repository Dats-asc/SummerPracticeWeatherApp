package com.example.summerpracticeweatherapp

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding
import com.example.summerpracticeweatherapp.databinding.FragmentTodayForecastBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.network.models.weather.Main
import com.example.summerpracticeweatherapp.utils.loadImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale


class TodayForecastFragment : Fragment(R.layout.fragment_today_forecast) {
    var binding : FragmentTodayForecastBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTodayForecastBinding.bind(view)
    }

    fun updateUI(weather: Main){
        binding?.run {
            tvCity.text = weather.city.name + ", ${weather.city.country}"
            tvMainTempValueNow.text= weather.list[0].main.temp.toString() + "°C"
            ivMainIconNow.loadImage("http://openweathermap.org/img/w/${weather.list[0].weather[0].icon}.png")
            tvInfDayAndNight.text = "Day ${weather.list[0].main.tempMax}°C " +
                    "Night ${weather.list[0].main.tempMin}°C"
            tvDataAndTime.text = Date().toString()
            tvPressureValue.text = weather.list[0].main.pressure.toString() + "hpa"
            tvWinSpeedValue.text = weather.list[0].wind.speed.toString() + "km/h"
            tvCloudInf.text = weather.list[0].weather[0].description
            tvFeelsLike.text = "Ощущается как " + weather.list[0].main.feelsLike.toString()
            ivWeatherNow.loadImage("http://openweathermap.org/img/w/${weather.list[0].weather[0].icon}.png")
            imWeatherAfterOneHour.loadImage("http://openweathermap.org/img/w/${weather.list[1].weather[0].icon}.png")
            imWeatherAfterTwoHour.loadImage("http://openweathermap.org/img/w/${weather.list[2].weather[0].icon}.png")
            imWeatherAfterThreeHour.loadImage("http://openweathermap.org/img/w/${weather.list[3].weather[0].icon}.png")
            imWeatherAfterFourHour.loadImage("http://openweathermap.org/img/w/${weather.list[4].weather[0].icon}.png")
            tvValueWeatherNow.text = weather.list[0].main.temp.toString() + "°C"
            tvValueWeatherAfter1.text= weather.list[1].main.temp.toString() + "°C"
            tvValueWeatherAfter2.text= weather.list[2].main.temp.toString() + "°C"
            tvValueWeatherAfter3.text= weather.list[3].main.temp.toString() + "°C"
            tvValueWeatherAfter4.text= weather.list[4].main.temp.toString() + "°C"
            tvTimeAfter1.text = weather.list[1].dtTxt.substring(10).reversed().substring(3).reversed()
            tvTimeAfter2.text = weather.list[2].dtTxt.substring(10).reversed().substring(3).reversed()
            tvTimeAfter3.text = weather.list[3].dtTxt.substring(10).reversed().substring(3).reversed()
            tvTimeAfter4.text = weather.list[4].dtTxt.substring(10).reversed().substring(3).reversed()
            tvRainChanceValue.text = weather.list[0].pop.toString().substring(2) + "%"
            tvUvIndexValue.text = weather.list[0].wind.deg.toString() + "°"

            //tvWeatherAfterOneHour.text = weather.list[0].dtTxt
        }
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
