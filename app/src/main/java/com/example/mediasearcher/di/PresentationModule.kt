package com.example.mediasearcher.di

import com.example.mediasearcher.presentation.details.books.BookDetailsViewModel
import com.example.mediasearcher.presentation.details.movies.MovieDetailsViewModel
import com.example.mediasearcher.presentation.details.series.SeriesDetailsViewModel
import com.example.mediasearcher.presentation.home.books.BooksViewModel
import com.example.mediasearcher.presentation.home.movies.MoviesViewModel
import com.example.mediasearcher.presentation.home.series.SeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { BooksViewModel(get()) }
    viewModel { BookDetailsViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { SeriesViewModel(get()) }
    viewModel { SeriesDetailsViewModel(get()) }
}
val presentationModule = listOf(
    viewModelsModule
)