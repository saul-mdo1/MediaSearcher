package com.example.mediasearcher.presentation.home.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mediasearcher.databinding.SeriesItemLayoutBinding
import com.example.mediasearcher.presentation.base.BaseRVAdapter
import timber.log.Timber

class SeriesRVAdapter(private val bookClicked: (SeriesItemViewModel) -> Unit) :
    BaseRVAdapter<SeriesItemViewModel, SeriesItemLayoutBinding, SeriesRVAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val itemBinding: SeriesItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: SeriesItemViewModel) {
            itemBinding.root.setOnClickListener {
                bookClicked(item)
            }
            Timber.d("ViewHolder_TAG: bind: ${item.title}")
        }
    }

    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SeriesItemLayoutBinding {
        layout = SeriesItemLayoutBinding.inflate(inflater, container, false)
        return layout
    }

    override fun getHolder(): ItemViewHolder {
        return ItemViewHolder(layout)
    }

    override fun setHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val series = itemList[position]
        val h = holder as ItemViewHolder
        h.itemBinding.viewModel = series
        h.bind(series)
        h.itemBinding.executePendingBindings()
    }
}