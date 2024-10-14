package com.example.mediasearcher.presentation.home.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mediasearcher.databinding.BookItemLayoutBinding
import com.example.mediasearcher.presentation.base.BaseRVAdapter
import timber.log.Timber

class BooksRVAdapter(private val bookClicked: (BookItemViewModel) -> Unit) :
    BaseRVAdapter<BookItemViewModel, BookItemLayoutBinding, BooksRVAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val itemBinding: BookItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: BookItemViewModel) {
            itemBinding.root.setOnClickListener {
                bookClicked(item)
            }
            Timber.d("ViewHolder_TAG: bind: ${item.title}")
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BookItemLayoutBinding {
        layout = BookItemLayoutBinding.inflate(inflater, container, false)
        return layout    }

    override fun getHolder(): ItemViewHolder {
        return ItemViewHolder(layout)
    }

    override fun setHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = itemList[position]
        val h = holder as ItemViewHolder
        h.itemBinding.viewModel = book
        h.bind(book)
        h.itemBinding.executePendingBindings()
    }
}