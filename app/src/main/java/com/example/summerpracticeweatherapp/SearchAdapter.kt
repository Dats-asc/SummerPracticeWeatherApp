package com.example.summerpracticeweatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.summerpracticeweatherapp.databinding.ItemCitySearchResultBinding
import com.example.summerpracticeweatherapp.network.models.weather.city.SearchResponse

class SearchAdapter(
    private val onItemClick: (SearchResponse) -> Unit
) : ListAdapter<SearchResponse, SearchViewHolder>(CALLBACK) {

    companion object {
        private val CALLBACK = object : DiffUtil.ItemCallback<SearchResponse>() {

            override fun areItemsTheSame(
                oldItem: SearchResponse,
                newItem: SearchResponse
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: SearchResponse,
                newItem: SearchResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemCitySearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

class SearchViewHolder(private val item: ItemCitySearchResultBinding) :
    RecyclerView.ViewHolder(item.root) {

    fun bind(city: SearchResponse, onItemClick: (SearchResponse) -> Unit) {
        item.tvCity.text = "${city.name}, ${city.country}"
        item.root.setOnClickListener {
            onItemClick(city)
        }
    }
}