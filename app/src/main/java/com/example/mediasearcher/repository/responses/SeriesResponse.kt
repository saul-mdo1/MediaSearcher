package com.example.mediasearcher.repository.responses

import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    @SerializedName("Title") val title: String?,
    @SerializedName("Rated") val rated: String?,
    @SerializedName("Released") val released: String?,
    @SerializedName("Runtime") val runtime: String?,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("Actors") val actors: String?,
    @SerializedName("Plot") val plot: String?,
    @SerializedName("Language") val language: String?,
    @SerializedName("Awards") val awards: String?,
    @SerializedName("Poster") val poster: String?,
    @SerializedName("Ratings") val ratings: List<RatingsResponse>?,
    @SerializedName("totalSeasons") val totalSeasons: String?
)

