package com.example.summerpracticeweatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val pages : List<InfoPage>) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewPagerAdapter.MyViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tv_info_header).text = pages[position].title
            findViewById<TextView>(R.id.tv_info).text = pages[position].text
        }
    }

    override fun getItemCount(): Int {
        return pages.size
    }
}