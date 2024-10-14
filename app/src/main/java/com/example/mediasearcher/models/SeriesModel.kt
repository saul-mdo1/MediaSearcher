package com.example.mediasearcher.models

data class SeriesModel(
val title: String?, //
val rated: String?, //
val released: String?, //
val runtime: String?, //
val genre: String?, //
val actors: String?, //
val plot: String?, //
val language: String?, //
val awards: String?, //
val poster: String?, //
val ratings: List<RatingsModel>?, //
val totalSeasons: String?
)
