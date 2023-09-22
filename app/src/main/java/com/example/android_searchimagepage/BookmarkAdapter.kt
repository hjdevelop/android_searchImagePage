package com.example.android_searchimagepage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_searchimagepage.databinding.GridItemBinding

class BookmarkAdapter(private val context : Context) : RecyclerView.Adapter<BookmarkAdapter.Holder>() {

    var bookmarkItem = mutableListOf<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = bookmarkItem[position]

        Glide.with(context)
            .load(item.imageUrl)
            .into(holder.image)

        holder.title.text = item.title
        holder.date.text = item.dateTime
        holder.bookmark.setImageResource(R.drawable.ic_filled_bookmark)
    }

    override fun getItemCount(): Int {
        return bookmarkItem.size
    }

    inner class Holder(binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.searchImageImageView
        val title = binding.searchTitleTextView
        val date = binding.searchDateTextView
        val bookmark = binding.bookmarkImageButton

        init {
            binding.bookmarkImageButton.setOnClickListener {
                val position = adapterPosition
                (context as MainActivity).removeBookmarkItem(bookmarkItem[position])
                val item = bookmarkItem[position]
                bookmarkItem.remove(item)
                notifyItemRemoved(position)
            }
        }
    }
}