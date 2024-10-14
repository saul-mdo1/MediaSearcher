package com.example.mediasearcher.di

import com.example.mediasearcher.repository.books.BooksAPIService
import com.example.mediasearcher.repository.books.BooksRepository
import com.example.mediasearcher.repository.moviesSeries.OMDbAPIService
import com.example.mediasearcher.repository.moviesSeries.OMDbRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BOOKS_BASE_URL = "https://www.googleapis.com/books/v1/"
private const val OMDB_BASE_URL = "http://www.omdbapi.com/"

inline fun <reified T> createRetrofitWebService(url: String): T =
    Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(T::class.java)

val bookRepositoryModule = module {
    single<BooksAPIService> { createRetrofitWebService(BOOKS_BASE_URL) }
    single { BooksRepository(get()) }
}

val OMBDbModule = module {
    single<OMDbAPIService> { createRetrofitWebService(OMDB_BASE_URL) }
    single { OMDbRepository(get()) }
}

val repositoryModule = listOf(
    bookRepositoryModule,
    OMBDbModule
)