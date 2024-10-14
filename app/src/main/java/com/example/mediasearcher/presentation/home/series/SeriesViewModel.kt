package com.example.mediasearcher.presentation.home.series

import androidx.lifecycle.MutableLiveData
import com.example.mediasearcher.models.MovieItemModel
import com.example.mediasearcher.models.SerieItemModel
import com.example.mediasearcher.presentation.base.BaseViewModel
import com.example.mediasearcher.repository.moviesSeries.OMDbRepository
import com.example.mediasearcher.utils.toMovieModel
import com.example.mediasearcher.utils.toSerieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SeriesViewModel(private val omdbRepository: OMDbRepository) : BaseViewModel() {
    val seriesList = MutableLiveData<List<SerieItemModel>>()
    val emptyList = MutableLiveData(true)
    val totalResults = MutableLiveData("0")
    val totalVisible = MutableLiveData(false)

    fun searchMovies(query: String) {
        Timber.d("BooksViewModel_TAG: searchMovies: ")
        CoroutineScope(Dispatchers.IO).launch {
            val apiCallResult = omdbRepository.searchMovieSerie(query, "series")
            if (apiCallResult.isSuccessful) {
                val success = apiCallResult.body()?.response == "True"

                if (success) {
                    val list = apiCallResult.body()?.search?.map { serieResponse ->
                        serieResponse.toSerieModel()
                    } ?: emptyList()
                    seriesList.postValue(list)

                    val total = list.size
                    totalVisible.postValue(list.isNotEmpty())
                    totalResults.postValue(total.toString())
                } else {
                    // EMPTY LIST OR ERROR API CALL
                }
            }

            emptyList.postValue(!apiCallResult.isSuccessful)
            loading.postValue(false)
        }
    }
}