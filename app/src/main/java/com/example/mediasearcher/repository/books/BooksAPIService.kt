package com.example.mediasearcher.repository.books

import com.example.mediasearcher.repository.responses.BookItemResponse
import com.example.mediasearcher.repository.responses.BooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksAPIService {
    @GET("volumes")
    suspend fun getBooksBySearch(
        @Query("q") query: String,
        @Query("maxResults") maxResults: String,
        @Query("langRestrict") lang: String? = null
    ): Response<BooksResponse>

    @GET("volumes/{id}")
    suspend fun getBookById(
        @Path("id") id: String,
        @Query("langRestrict") lang: String? = null
    ): Response<BookItemResponse>
}