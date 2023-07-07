package com.example.summerpracticeweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding
import com.example.summerpracticeweatherapp.utils.SharedPrefsUtils


class SearchFragment : Fragment(R.layout.fragment_search) {
    var binding: FragmentSearchBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        binding?.let {
            it.btnSaveCity.setOnClickListener {
                val cityName = binding?.etCity?.text.toString()
                SharedPrefsUtils.saveCity(requireContext(), cityName)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}