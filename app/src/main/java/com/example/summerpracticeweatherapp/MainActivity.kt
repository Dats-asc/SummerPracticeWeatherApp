package com.example.summerpracticeweatherapp

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.summerpracticeweatherapp.databinding.ActivityMainBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.utils.SharedPrefsUtils
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val controller =
            (supportFragmentManager.findFragmentById(R.id.cw_main_container) as NavHostFragment)
                .navController
        findViewById<BottomNavigationView>(R.id.navBar).apply {
            setupWithNavController(controller)
        }
        if (SharedPrefsUtils.isOpened(this)){
            controller.navigate(R.id.action_infoFragment_to_searchFragment)
        }




        Forecast.init(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        Forecast.clear()
    }
}