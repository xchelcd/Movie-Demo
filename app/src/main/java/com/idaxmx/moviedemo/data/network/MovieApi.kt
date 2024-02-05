package com.idaxmx.moviedemo.data.network

import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.data.model.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/now_playing")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<NowPlayingResponse>

    @GET("3/movie/{id}")
    suspend fun getMovieById(@Path("id") id: Long): Response<Movie>
}