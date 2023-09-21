package com.example.android_searchimagepage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_searchimagepage.databinding.GridItemBinding

class CustomAdapter(private val context : Context) : RecyclerView.Adapter<CustomAdapter.Holder>() {

    var searchItem = mutableListOf<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = searchItem[position]

        Glide.with(context)
            .load(item.imageUrl)
            .into(holder.image)

        holder.title.text = item.title
        holder.date.text = item.dateTime
    }

    override fun getItemCount(): Int {
        return searchItem.size
    }

    inner class Holder(binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.searchImageImageView
        val title = binding.searchTitleTextView
        val date = binding.searchDateTextView
    }
}