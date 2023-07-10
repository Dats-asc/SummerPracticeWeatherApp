package com.example.summerpracticeweatherapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.summerpracticeweatherapp.DayRepository.calcMaxTemp
import com.example.summerpracticeweatherapp.DayRepository.calcMinTemp
import com.example.summerpracticeweatherapp.DayRepository.loadData
import com.example.summerpracticeweatherapp.DayRepository.startDayIndex
import com.example.summerpracticeweatherapp.databinding.FragmentGraphForecastBinding
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.network.models.weather.Main
import com.example.summerpracticeweatherapp.network.models.weather.Weather
import com.google.android.material.snackbar.Snackbar
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.DataPointInterface
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.OnDataPointTapListener
import com.jjoe64.graphview.series.Series
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphForecastFragment : Fragment(R.layout.fragment_graph_forecast) {
    var binding : FragmentGraphForecastBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGraphForecastBinding.bind(view)
    }

    fun updateUI(weather: Main){
        loadData(weather)
        binding?.tvCity?.text = weather.city.name
        val graphView = binding?.graph


        val dataPointsMax = DayRepository.maxTempArray.mapIndexed { index, temperature ->
            DataPoint(index.toDouble(), temperature)
        }.toTypedArray()
        val dataPointsMin = DayRepository.minTempArray.mapIndexed { index, temperature ->
            DataPoint(index.toDouble(), temperature)
        }.toTypedArray()

        val seriesMax = LineGraphSeries(dataPointsMax)
        seriesMax.color = Color.RED
        val seriesMin = LineGraphSeries(dataPointsMin)
        seriesMin.color = Color.BLUE

        graphView?.addSeries(seriesMax)
        graphView?.addSeries(seriesMin)

        val tapListener = object : OnDataPointTapListener {
            override fun onTap(series: Series<DataPointInterface>, dataPoint: DataPointInterface) {
                val temperature = dataPoint.y.toInt().toString()
                val day = dataPoint.x.toInt() + 1
                val message = "Temperature on day $day: $temperature°C"
                Snackbar.make(binding?.root as View, message, Snackbar.LENGTH_SHORT).show()
            }
        }

        seriesMax.setOnDataPointTapListener(tapListener)
        seriesMin.setOnDataPointTapListener(tapListener)

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