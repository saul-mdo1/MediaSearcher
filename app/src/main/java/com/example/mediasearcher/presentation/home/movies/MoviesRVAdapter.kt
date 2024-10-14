package com.example.mediasearcher.presentation.home.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mediasearcher.databinding.MovieItemLayoutBinding
import com.example.mediasearcher.presentation.base.BaseRVAdapter
import timber.log.Timber

class MoviesRVAdapter(private val bookClicked: (MovieItemViewModel) -> Unit) :
    BaseRVAdapter<MovieItemViewModel, MovieItemLayoutBinding, MoviesRVAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val itemBinding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: MovieItemViewModel) {
            itemBinding.root.setOnClickListener {
                bookClicked(item)
            }
            Timber.d("ViewHolder_TAG: bind: ${item.title}")
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MovieItemLayoutBinding {
        layout = MovieItemLayoutBinding.inflate(inflater, container, false)
        return layout
    }

    override fun getHolder(): ItemViewHolder {
        return ItemViewHolder(layout)
    }

    override fun setHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = itemList[position]
        val h = holder as ItemViewHolder
        h.itemBinding.viewModel = movie
        h.bind(movie)
        h.itemBinding.executePendingBindings()
    }
}