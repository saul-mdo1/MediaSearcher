package com.example.mediasearcher.presentation.home.movies

import androidx.lifecycle.MutableLiveData
import com.example.mediasearcher.models.MovieItemModel
import com.example.mediasearcher.presentation.base.BaseViewModel
import com.example.mediasearcher.repository.moviesSeries.OMDbRepository
import com.example.mediasearcher.utils.toMovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MoviesViewModel(private val omdbRepository: OMDbRepository) : BaseViewModel() {
    val moviesList = MutableLiveData<List<MovieItemModel>>()
    val emptyList = MutableLiveData(true)
    val totalResults = MutableLiveData("0")
    val totalVisible = MutableLiveData(false)

    fun searchMovies(query: String) {
        Timber.d("BooksViewModel_TAG: searchMovies: ")
        CoroutineScope(Dispatchers.IO).launch {
            val apiCallResult = omdbRepository.searchMovieSerie(query)
            if (apiCallResult.isSuccessful) {
                val success = apiCallResult.body()?.response == "True"

                if (success) {
                    val list = apiCallResult.body()?.search?.map { movieResponse ->
                        movieResponse.toMovieModel()
                    } ?: emptyList()
                    moviesList.postValue(list)

                    val total = list.size
                    totalVisible.postValue(list.isNotEmpty())
                    totalResults.postValue(total.toString())
                } else {
                    totalVisible.postValue(true)
                    totalResults.postValue("0")
                }
            }

            emptyList.postValue(!apiCallResult.isSuccessful)
            loading.postValue(false)
        }
    }
}