package com.example.mediasearcher.repository.responses

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("items") val items: List<BookItemResponse>
)

data class BookItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: BookInfoResponse
)

data class BookInfoResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("authors") val authors: List<String>?,
    @SerializedName("publisher") val publisher: String?,
    @SerializedName("publishedDate") val publishedDate: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("pageCount") val pageCount: Int?,
    @SerializedName("averageRating") val averageRating: Float?,
    @SerializedName("imageLinks") val imageLinks: ImageLinksResponse?,
    @SerializedName("industryIdentifiers") val industryIdentifiers: List<IndustryIdentifiersResponse>?
)

data class ImageLinksResponse(
    @SerializedName("smallThumbnail") val smallThumbnail: String?,
    @SerializedName("thumbnail") val thumbnail: String?
)

data class IndustryIdentifiersResponse(
    @SerializedName("type") val type: String?,
    @SerializedName("identifier") val identifier: String?
)