package com.example.summerpracticeweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.summerpracticeweatherapp.databinding.FragmentDaysBinding
import com.example.summerpracticeweatherapp.network.NetworkManager
import com.example.summerpracticeweatherapp.utils.SharedPrefsUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onStart() {
        super.onStart()
        savedCity = SharedPrefsUtils.getSavedCity(requireContext()) ?: ""
        binding?.tvCity?.text = savedCity
        coroutineScope.launch(Dispatchers.IO) {
            val weather = weatherService.getWeatherByCity(savedCity!!)
            withContext(Dispatchers.Main) {
                binding?.tvTemp?.text = weather.main.temp.toString() + "°C"
            }
        }
    }

    private fun initAdapter() {
        adapter = DayAdapter(DayRepository.list)
        binding?.rvDay?.adapter = adapter
        binding?.tvCity?.text = savedCity
    }
}
