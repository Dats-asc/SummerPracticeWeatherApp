package com.example.summerpracticeweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.summerpracticeweatherapp.databinding.FragmentGraphForecastBinding
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding

class GraphForecastFragment : Fragment(R.layout.fragment_graph_forecast) {
    var binding : FragmentGraphForecastBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGraphForecastBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}