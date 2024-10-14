package com.example.mediasearcher.repository.moviesSeries

import com.example.mediasearcher.repository.responses.MovieResponse
import com.example.mediasearcher.repository.responses.OMDbListResponse
import com.example.mediasearcher.repository.responses.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbAPIService {
    @GET("/")
    suspend fun getBySearch(
        @Query("s") query: String,
        @Query("type") type: String = "movie",
        @Query("apikey") apiKey: String
    ): Response<OMDbListResponse>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): Response<MovieResponse>

    @GET("/")
    suspend fun getSerieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): Response<SeriesResponse>
}