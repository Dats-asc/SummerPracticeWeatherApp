package com.example.summerpracticeweatherapp
import androidx.recyclerview.widget.RecyclerView
import com.example.summerpracticeweatherapp.databinding.ItemDayBinding
import com.example.summerpracticeweatherapp.network.Forecast
import com.example.summerpracticeweatherapp.utils.loadImage


class DayItem(private val binding: ItemDayBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(day: Day){
        binding.run{
            tvData.text = day.date
            tvDescription.text = day.description
            tvMaxTemp.text = day.maxTemp.toString()
            tvMinTemp.text = day.minTemp.toString()
            ivIcon.loadImage("http://openweathermap.org/img/w/${day.icon}.png")
        }
    }
}