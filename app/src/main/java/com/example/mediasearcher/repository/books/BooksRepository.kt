package com.example.mediasearcher.repository.books

class BooksRepository(private val booksApi: BooksAPIService) {
    suspend fun searchBooks(query: String, lang: String? = null) =
        booksApi.getBooksBySearch(query, "40", lang)

    suspend fun getBookById(id: String, lang: String? = null) = booksApi.getBookById(id, lang)
}