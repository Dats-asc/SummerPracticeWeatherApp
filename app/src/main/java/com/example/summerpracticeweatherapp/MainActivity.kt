package com.example.summerpracticeweatherapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.summerpracticeweatherapp.databinding.ActivityMainBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.network.NetworkManager
import com.example.summerpracticeweatherapp.network.models.weather.Main
import com.example.summerpracticeweatherapp.network.models.weather.WeatherResponse
import com.example.summerpracticeweatherapp.utils.SharedPrefsUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
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

        Forecast.init(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        Forecast.clear()
    }
}