package com.example.mediasearcher.presentation.details.books

import androidx.lifecycle.MutableLiveData
import com.example.mediasearcher.models.BookModel
import com.example.mediasearcher.presentation.base.BaseViewModel
import com.example.mediasearcher.repository.books.BooksRepository
import com.example.mediasearcher.utils.formatDate
import com.example.mediasearcher.utils.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class BookDetailsViewModel(private val booksRepository: BooksRepository) : BaseViewModel() {
    val book = MutableLiveData<BookModel?>()

    val authors = MutableLiveData("")
    val publishedDate = MutableLiveData("")
    val publisherVisible = MutableLiveData(true)
    val publishedDateVisible = MutableLiveData(true)
    val separationPublisherVisible = MutableLiveData(true)
    val isbnVisible = MutableLiveData(true)

    fun getBookById(id: String) {
        Timber.d("BookDetailsViewModel_TAG: getBookById: $id ")
        CoroutineScope(Dispatchers.IO).launch {
            val result = booksRepository.getBookById(id)
            if (result.isSuccessful) {
                result.body()?.volumeInfo?.toModel()?.let { bookResult ->
                    book.postValue(bookResult)
                    setVisibilities(bookResult)
                }
            }
        }
    }

    private fun setVisibilities(bookResult: BookModel) {
        Timber.d("BookDetailsViewModel_TAG: setVisibilities: ")
        val isPublisherVisible = !bookResult.publisher.isNullOrBlank()
        val isDateVisible = !bookResult.publishedDate.isNullOrBlank()
        val isIsbnVisible = !bookResult.isbn.isNullOrBlank()
        val dateFormatted = formatDate(bookResult.publishedDate)
        val authorsFormatted = bookResult.authors?.joinToString(" | ")

        authors.postValue(authorsFormatted)
        publishedDate.postValue(dateFormatted)
        publisherVisible.postValue(isPublisherVisible)
        publishedDateVisible.postValue(isDateVisible)
        separationPublisherVisible.postValue(isPublisherVisible && isDateVisible)
        isbnVisible.postValue(isIsbnVisible)

        loading.postValue(false)
    }
}