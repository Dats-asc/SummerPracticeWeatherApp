package com.example.summerpracticeweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.summerpracticeweatherapp.databinding.ActivityMainBinding
import com.example.summerpracticeweatherapp.network.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val weatherService by lazy {
        NetworkManager.getWeatherService()
    }

    private val coroutineScope = CoroutineScope(SupervisorJob())

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coroutineScope.launch {
            val weather = weatherService.getWeather()
            delay(2000)
            withContext(Dispatchers.Main) {
                binding.tvTest.text = "teasdasdasd"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}