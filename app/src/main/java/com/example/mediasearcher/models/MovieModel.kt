package com.example.mediasearcher.models

data class MovieModel(
    val title: String?,
    val rated: String?,
    val released: String?,
    val runtime: String?,
    val genre: String?,
    val director: String?,
    val actors: String?,
    val plot: String?,
    val language: String?,
    val awards: String?,
    val poster: String?,
    val ratings: List<RatingsModel>?,
    val boxOffice: String?
)

data class RatingsModel(
    val source: String?,
    val value: String?
)
