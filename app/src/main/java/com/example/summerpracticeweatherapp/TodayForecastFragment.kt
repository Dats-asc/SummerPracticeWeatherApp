package com.example.summerpracticeweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding
import com.example.summerpracticeweatherapp.databinding.FragmentTodayForecastBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.utils.loadImage


class TodayForecastFragment : Fragment(R.layout.fragment_today_forecast) {
    var binding : FragmentTodayForecastBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTodayForecastBinding.bind(view)
        binding?.let {
            val forecast =  Forecast.forecast!!
            with(it){
                tvCity1.text = Forecast.forecast?.city?.name
                //imageView.loadImage("https://openweathermap.org/img/w/10d.png")
                ivMainIconNow.loadImage("http://openweathermap.org/img/w/${forecast.list[0].weather[0].icon}.png")
                ivWeatherNow.loadImage("http://openweathermap.org/img/w/${forecast.list[0].weather[0].icon}.png")
                tvMainTempValueNow.text = "${forecast.list[0].main.temp.toString()}°"
                tvInfDayAndNight.text = "Day ${forecast.list[0].main.tempMax.toString()}° " +
                        "Night ${forecast.list[0].main.tempMin.toString()}°"
                tvDataAndTime.text = forecast.list[0].dtTxt

            }

        }

    }
//    fun dataTime(){
//        val data = Forecast.forecast!!.list[0].dtTxt
//        when()
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}