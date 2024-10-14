package com.example.mediasearcher.presentation.home.books

import androidx.lifecycle.MutableLiveData
import com.example.mediasearcher.models.BookModel
import com.example.mediasearcher.presentation.base.BaseViewModel
import com.example.mediasearcher.repository.books.BooksRepository
import com.example.mediasearcher.utils.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class BooksViewModel(private val booksRepository: BooksRepository) : BaseViewModel() {
    val booksList = MutableLiveData<List<BookModel>>()
    val emptyList = MutableLiveData(true)
    val totalResults = MutableLiveData("0")
    val totalVisible = MutableLiveData(false)

    fun searchBooks(query: String) {
        Timber.d("BooksViewModel_TAG: searchMovies: ")
        CoroutineScope(Dispatchers.IO).launch {
            val result = booksRepository.searchBooks(query)
            if (result.isSuccessful) {
                val list = result.body()?.items?.map { bookResponse ->
                    bookResponse.volumeInfo.toModel(bookResponse.id)
                }
                booksList.postValue(list ?: emptyList())
                totalVisible.postValue(list?.isNotEmpty())
                val total = list?.size ?: 0
                totalResults.postValue(total.toString())
            }

            emptyList.postValue(!result.isSuccessful)
            loading.postValue(false)
        }
    }
}