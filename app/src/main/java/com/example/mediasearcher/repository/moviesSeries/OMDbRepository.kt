package com.example.mediasearcher.repository.moviesSeries

class OMDbRepository(private val omdbApi: OMDbAPIService) {
    private val OMDB_API_KEY = "6deca021"

    suspend fun searchMovieSerie(query: String, type: String = "movie") =
        omdbApi.getBySearch(query, type, OMDB_API_KEY)

    suspend fun getMovieById(id: String) = omdbApi.getMovieDetails(id, OMDB_API_KEY)

    suspend fun getSeriesById(id: String) = omdbApi.getSerieDetails(id, OMDB_API_KEY)
}