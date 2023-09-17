package com.example.android_searchimagepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_searchimagepage.databinding.GridItemBinding
import java.text.FieldPosition

class CustomAdapter(val item : MutableList<SearchData>) : RecyclerView.Adapter<CustomAdapter.Holder>() {
    interface ItemClick {
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.image.setImageResource(item[position].image)
        holder.title.text = item[position].title
        holder.date.text = item[position].dateTime
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class Holder(val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.searchImageImageView
        val title = binding.searchTitleTextView
        val date = binding.searchDateTextView
    }
}