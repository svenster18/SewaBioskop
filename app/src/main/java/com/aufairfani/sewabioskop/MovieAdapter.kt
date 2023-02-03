package com.aufairfani.sewabioskop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val listMovie: List<ItemsItem>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_movie, viewGroup, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(viewHolder.itemView.context)
            .load(listMovie[position].image)
            .into(viewHolder.ivMovie)
        viewHolder.tvTitle.text = listMovie[position].title
        viewHolder.tvDescription.text = "${listMovie[position].year} \u2022 ${listMovie[position].genres}"
        viewHolder.tvRating.text = listMovie[position].imDbRating
        val hour = listMovie[position].runtimeMins.toInt() / 60
        val min = listMovie[position].runtimeMins.toInt() % 60
        viewHolder.tvDuration.text = "${hour}h${min}m"
    }
    override fun getItemCount(): Int = listMovie.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivMovie : ImageView = view.findViewById(R.id.iv_movie)
        val tvTitle : TextView = view.findViewById(R.id.tv_title)
        val tvDescription : TextView = view.findViewById(R.id.tv_description)
        val tvRating : TextView = view.findViewById(R.id.tv_rating)
        val tvDuration : TextView = view.findViewById(R.id.tv_duration)
    }
}