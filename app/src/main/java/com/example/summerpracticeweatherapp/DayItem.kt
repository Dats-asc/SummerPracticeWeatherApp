package com.example.summerpracticeweatherapp
import androidx.recyclerview.widget.RecyclerView
import com.example.summerpracticeweatherapp.databinding.ItemDayBinding


class DayItem(private val binding: ItemDayBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(day: Day){
        binding.run{
            tvTitle.text = day.name
            tvDesc.text = day.status.toString()
        }
    }
}