package com.example.mediasearcher.repository.responses

import com.google.gson.annotations.SerializedName

data class OMDbListResponse(
    @SerializedName("Search") val search: List<OMDbItemResponse>,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Response") val response: String,
    @SerializedName("Error") val error: String?
)

data class OMDbItemResponse(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String?,
    @SerializedName("imdbID") val imdbID: String?,
    @SerializedName("Poster") val poster: String?
)