package com.example.mediasearcher.models

data class BookModel(
    var id: String,
    val title: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val pageCount: Int,
    val averageRating: Float,
    val imageThumbnail: String?,
    val thumbnail: String?,
    val isbn: String?
)