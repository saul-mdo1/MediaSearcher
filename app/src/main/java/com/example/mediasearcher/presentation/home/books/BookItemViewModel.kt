package com.example.mediasearcher.presentation.home.books

import com.example.mediasearcher.models.BookModel

class BookItemViewModel {
    var book: BookModel? = null

    val id: String
        get() = book?.id ?: ""

    val title: String
        get() = book?.title ?: "---"

    val authors: String
        get() {
            val authorsFormatted = book?.authors?.joinToString(" | ")
            return authorsFormatted ?: "---"
        }

    val image: String?
        get() = book?.thumbnail
}