package com.example.summerpracticeweatherapp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.summerpracticeweatherapp.databinding.ActivityMainBinding
import com.example.summerpracticeweatherapp.network.NetworkManager
import com.example.summerpracticeweatherapp.network.models.weather.WeatherResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val weatherService by lazy {
        NetworkManager.getWeatherService()
    }

    val weatherState: MutableLiveData<WeatherResponse?> = MutableLiveData()

    private val coroutineScope = CoroutineScope(SupervisorJob())

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val controller =
            (supportFragmentManager.findFragmentById(R.id.cw_main_container) as NavHostFragment)
                .navController
        findViewById<BottomNavigationView>(R.id.navBar).apply {
            setupWithNavController(controller)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)


        /*
        coroutineScope.launch(Dispatchers.IO) {
            val currentWeather = weatherService.getWeatherByCity("Kazan")
            withContext(Dispatchers.Main) {
                weatherState.value = currentWeather
            }
        }

         */
    }

    override fun onStart() {
        super.onStart()
        weatherState.observe(this) { weather ->
            weather?.let {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}