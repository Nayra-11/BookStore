package com.example.bookstore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.databinding.ItemBookBinding

class BookAdapter(private val books: MutableList<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.binding.book = book
        holder.binding.executePendingBindings()
        holder.binding.bookImage.setImageResource(book.imageResId)


        holder.binding.closeIcon.setOnClickListener {
            removeItem(position)
        }


        holder.binding.bookRating.setOnRatingBarChangeListener { _, rating, _ ->
            book.rating = rating
            notifyItemChanged(position)
        }


        holder.binding.wantToReadButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${book.title} marked as Want to Read", Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int {
        return books.size
    }


    private fun removeItem(position: Int) {
        books.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, books.size)
    }
}