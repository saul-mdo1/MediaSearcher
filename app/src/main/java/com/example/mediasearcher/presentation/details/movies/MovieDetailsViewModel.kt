package com.example.mediasearcher.presentation.details.movies

import androidx.lifecycle.MutableLiveData
import com.example.mediasearcher.models.MovieItemModel
import com.example.mediasearcher.models.MovieModel
import com.example.mediasearcher.presentation.base.BaseViewModel
import com.example.mediasearcher.repository.moviesSeries.OMDbRepository
import com.example.mediasearcher.utils.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailsViewModel(private val moviesRepository: OMDbRepository) : BaseViewModel() {
    val movie = MutableLiveData<MovieModel?>()

    fun getMovieById(id: String) {
        Timber.d("MovieDetailsViewModel_TAG: getMovieById: $id ")
        CoroutineScope(Dispatchers.IO).launch {
            val result = moviesRepository.getMovieById(id)
            if (result.isSuccessful) {
                val m = result.body()
                movie.postValue(m?.toModel())
                loading.postValue(false)
            }
        }
    }
}