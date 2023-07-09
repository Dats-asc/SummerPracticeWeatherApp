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


class TodayForecastFragment : Fragment(R.layout.fragment_today_forecast) {
    var binding : FragmentTodayForecastBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTodayForecastBinding.bind(view)
        binding?.let {
            with(it){
                textView3.text = Forecast.forecast?.city?.name
                Glide.with(requireContext())
                    .load("http://openweathermap.org/img/w/10d.png")
                    .into(imageView3)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}