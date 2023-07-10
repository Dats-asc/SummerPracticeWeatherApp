package com.example.summerpracticeweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.network.NetworkManager
import com.example.summerpracticeweatherapp.network.models.weather.Main
import com.example.summerpracticeweatherapp.utils.SharedPrefsUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchFragment : Fragment(R.layout.fragment_search) {
    var binding: FragmentSearchBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        binding?.etCity?.setText(SharedPrefsUtils.getSavedCity(requireContext()))

        binding?.let {
            it.btnSaveCity.setOnClickListener {
                Forecast.updateForecast(requireContext(), binding?.etCity?.text.toString())
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}