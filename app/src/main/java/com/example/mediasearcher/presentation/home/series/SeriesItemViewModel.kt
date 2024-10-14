package com.example.mediasearcher.presentation.home.series

import com.example.mediasearcher.models.SerieItemModel

class SeriesItemViewModel {
    var serie: SerieItemModel? = null

    val id: String
        get() = serie?.imdbID ?: ""

    val title: String
        get() = serie?.title ?: "---"

    val year: String?
        get() = serie?.year

    val poster: String?
        get() = serie?.poster
}