package com.example.summerpracticeweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.summerpracticeweatherapp.databinding.ActivityMainBinding
import com.example.summerpracticeweatherapp.network.NetworkManager
import com.example.summerpracticeweatherapp.network.models.weather.WeatherResponse
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

    val weatherState: MutableLiveData<WeatherResponse?> = MutableLiveData()

    private val coroutineScope = CoroutineScope(SupervisorJob())

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coroutineScope.launch(Dispatchers.IO) {
            val currentWeather = weatherService.getWeatherByCity("Kazan")
            withContext(Dispatchers.Main) {
                weatherState.value = currentWeather
            }
        }
    }

    override fun onStart() {
        super.onStart()
        weatherState.observe(this) { weather ->
            weather?.let {
                binding.tvTest.text = "Now is: ${weather.main.temp}"
            }
        }
        with(binding) {
            etTest.setOnDebounceTextChanged(coroutineScope) {
                tvTest.text = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}

fun EditText.setOnDebounceTextChanged(
    coroutineScope: CoroutineScope,
    onTextChanged: (String) -> Unit
) {
    this.addTextChangedListener {
        coroutineScope.launch(Dispatchers.Main) {
            if (it.toString().isEmpty()) return@launch
            delay(1000)
            onTextChanged(it.toString())
        }
    }
}