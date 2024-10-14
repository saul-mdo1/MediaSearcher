package com.example.mediasearcher.presentation.details.series

import androidx.lifecycle.MutableLiveData
import com.example.mediasearcher.models.MovieItemModel
import com.example.mediasearcher.models.MovieModel
import com.example.mediasearcher.models.SeriesModel
import com.example.mediasearcher.presentation.base.BaseViewModel
import com.example.mediasearcher.repository.moviesSeries.OMDbRepository
import com.example.mediasearcher.utils.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SeriesDetailsViewModel(private val moviesRepository: OMDbRepository) : BaseViewModel() {
    val serie = MutableLiveData<SeriesModel?>()

    fun getSeriesById(id: String) {
        Timber.d("MovieDetailsViewModel_TAG: getSeriesById: $id ")
        CoroutineScope(Dispatchers.IO).launch {
            val result = moviesRepository.getSeriesById(id)
            if (result.isSuccessful) {
                val m = result.body()
                serie.postValue(m?.toModel())
                loading.postValue(false)
            }
        }
    }
}