package com.idaxmx.moviedemo.data.network

import com.idaxmx.moviedemo.data.model.GenericMovieResponse
import com.idaxmx.moviedemo.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/now_playing")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<GenericMovieResponse>

    @GET("3/movie/{id}")
    suspend fun getMovieById(@Path("id") id: Long): Response<Movie>

    @GET("3/search/movie")
    suspend fun getMovieByTitle(@Query("query") title: String): Response<GenericMovieResponse>
}