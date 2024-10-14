package com.example.mediasearcher.repository.responses

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Title") val title: String?,
    @SerializedName("Rated") val rated: String?,
    @SerializedName("Released") val released: String?,
    @SerializedName("Runtime") val runtime: String?,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("Director") val director: String?,
    @SerializedName("Actors") val actors: String?,
    @SerializedName("Plot") val plot: String?,
    @SerializedName("Language") val language: String?,
    @SerializedName("Awards") val awards: String?,
    @SerializedName("Poster") val poster: String?,
    @SerializedName("Ratings") val ratings: List<RatingsResponse>?,
    @SerializedName("BoxOffice") val boxOffice: String?
)

data class RatingsResponse(
    @SerializedName("Source") val source: String?,
    @SerializedName("Value") val value: String?
)