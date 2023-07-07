package com.example.summerpracticeweatherapp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.summerpracticeweatherapp.databinding.FragmentDaysBinding

class DaysFragment : Fragment(R.layout.fragment_days) {
    private var binding: FragmentDaysBinding? = null
    private var adapter: DayAdapter?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDaysBinding.bind(view)
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initAdapter(){
        adapter = DayAdapter(DayRepository.list)
        binding?.rvDay?.adapter = adapter
    }
}
