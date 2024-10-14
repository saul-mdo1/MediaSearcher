package com.example.mediasearcher.presentation.home.movies

import com.example.mediasearcher.models.MovieItemModel

class MovieItemViewModel {
    var movie: MovieItemModel? = null

    val id: String
        get() = movie?.imdbID ?: ""

    val title: String
        get() = movie?.title ?: "---"

    val year: String?
        get() = movie?.year

    val poster: String?
        get() = movie?.poster
}