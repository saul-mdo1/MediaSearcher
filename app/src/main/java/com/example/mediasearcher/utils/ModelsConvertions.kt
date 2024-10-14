package com.example.mediasearcher.utils

import com.example.mediasearcher.models.BookModel
import com.example.mediasearcher.models.MovieItemModel
import com.example.mediasearcher.models.MovieModel
import com.example.mediasearcher.models.RatingsModel
import com.example.mediasearcher.models.SerieItemModel
import com.example.mediasearcher.models.SeriesModel
import com.example.mediasearcher.repository.responses.BookInfoResponse
import com.example.mediasearcher.repository.responses.MovieResponse
import com.example.mediasearcher.repository.responses.OMDbItemResponse
import com.example.mediasearcher.repository.responses.RatingsResponse
import com.example.mediasearcher.repository.responses.SeriesResponse

fun BookInfoResponse.toModel(id: String = "") = BookModel(
    id = id,
    title = this.title,
    authors = this.authors,
    publisher = this.publisher,
    publishedDate = this.publishedDate,
    description = this.description,
    pageCount = this.pageCount ?: 0,
    averageRating = this.averageRating ?: 0f,
    imageThumbnail = this.imageLinks?.smallThumbnail,
    thumbnail = this.imageLinks?.thumbnail,
    isbn = this.industryIdentifiers?.lastOrNull()?.identifier
)

fun OMDbItemResponse.toMovieModel() = MovieItemModel(
    imdbID = this.imdbID,
    title = this.title,
    year = this.year,
    poster = this.poster
)

fun OMDbItemResponse.toSerieModel() = SerieItemModel(
    imdbID = this.imdbID,
    title = this.title,
    year = this.year,
    poster = this.poster
)

fun MovieResponse.toModel() = MovieModel(
    title = this.title,
    rated = this.rated,
    released = this.released,
    runtime = this.runtime,
    genre = this.genre,
    director = this.director,
    actors = this.actors,
    plot = this.plot,
    language = this.language,
    awards = this.awards,
    poster = this.poster,
    ratings = this.ratings?.map { it.toModel() },
    boxOffice = this.boxOffice
)

fun RatingsResponse.toModel() = RatingsModel(
    source = this.source,
    value = this.value
)

fun SeriesResponse.toModel() = SeriesModel(
    title = this.title,
    rated = this.rated,
    released = this.released,
    runtime = this.runtime,
    genre = this.genre,
    actors = this.actors,
    plot = this.plot,
    language = this.language,
    awards = this.awards,
    poster = this.poster,
    ratings = this.ratings?.map { it.toModel() },
    totalSeasons = this.totalSeasons
)