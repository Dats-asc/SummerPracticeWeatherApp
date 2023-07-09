package com.example.summerpracticeweatherapp
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.summerpracticeweatherapp.databinding.ItemDayBinding

class DayAdapter(
    private var list: List<Day>,
) : RecyclerView.Adapter<DayItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DayItem = DayItem(
        ItemDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
    )

    override fun onBindViewHolder(holder: DayItem, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}