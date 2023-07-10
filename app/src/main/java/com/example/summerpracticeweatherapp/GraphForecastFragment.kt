package com.example.summerpracticeweatherapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.summerpracticeweatherapp.databinding.FragmentGraphForecastBinding
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.DataPointInterface
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.OnDataPointTapListener
import com.jjoe64.graphview.series.Series

class GraphForecastFragment : Fragment(R.layout.fragment_graph_forecast) {
    var binding : FragmentGraphForecastBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGraphForecastBinding.bind(view)

        val graphView = binding?.graph

        val maxTemperatures = listOf(25.5, 28.3, 26.8, 30.0, 27.7)
        val minTemperatures = listOf(15.2, 16.7, 14.9, 18.5, 16.1)

        val dataPointsMax = maxTemperatures.mapIndexed { index, temperature ->
            DataPoint(index.toDouble(), temperature)
        }.toTypedArray()

        val dataPointsMin = minTemperatures.mapIndexed { index, temperature ->
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
                Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
            }
        }

        seriesMax.setOnDataPointTapListener(tapListener)
        seriesMin.setOnDataPointTapListener(tapListener)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}