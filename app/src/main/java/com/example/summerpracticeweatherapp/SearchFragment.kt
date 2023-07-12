package com.example.summerpracticeweatherapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.summerpracticeweatherapp.databinding.FragmentSearchBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.network.NetworkManager
import com.example.summerpracticeweatherapp.utils.SharedPrefsUtils
import com.example.summerpracticeweatherapp.utils.setOnDebounceTextChanged
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchFragment : Fragment(R.layout.fragment_search) {
    var binding: FragmentSearchBinding? = null

    private val searchService = NetworkManager.getSearchService()

    private var searchAdapter: SearchAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        binding?.etCity?.setText(SharedPrefsUtils.getSavedCity(requireContext()))

        activity?.let {
            it.findViewById<BottomNavigationView>(R.id.navBar).visibility = View.VISIBLE
        }

        if (searchAdapter == null) {
            searchAdapter = SearchAdapter { city ->
                binding?.etCity?.setText(city.name)
            }
            binding?.rvCities?.adapter = searchAdapter
        }
        binding?.let {
            it.etCity.setOnDebounceTextChanged(lifecycleScope) { query ->
                if (query.isNotEmpty()) {
                    lifecycleScope.launch(Dispatchers.IO) {

                        val cities =
                            searchService.getCitiesByRequest(binding?.etCity?.text.toString())

                        withContext(Dispatchers.Main) {
                            if (cities.isEmpty()) {
                                binding?.tvNotFoundStub?.visibility = View.VISIBLE
                                binding?.rvCities?.visibility = View.GONE
                            } else {
                                binding?.tvNotFoundStub?.visibility = View.GONE
                                binding?.rvCities?.visibility = View.VISIBLE
                            }

                            searchAdapter?.let {
                                it.submitList(cities)
                            }
                        }
                    }
                }
            }
            it.btnSaveCity.setOnClickListener {
                Forecast.updateForecast(requireContext(), binding?.etCity?.text.toString())

                lifecycleScope.launch(Dispatchers.IO) {
                    val cities = searchService.getCitiesByRequest(binding?.etCity?.text.toString())

                    withContext(Dispatchers.Main) {
                        searchAdapter?.let {
                            it.submitList(cities)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}