package com.idaxmx.moviedemo.domain

import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.data.model.NowPlayingResponse
import com.idaxmx.moviedemo.data.repository.MovieRepository
import com.idaxmx.moviedemo.di.NetworkModule
import com.idaxmx.moviedemo.util.network_response.Resource
import com.idaxmx.moviedemo.util.network_response.WrapperResponse
import javax.inject.Inject

class FetchMovieById @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Long): WrapperResponse<Movie?> {
        return when (val moviesRes: Resource = repository.getMovieById(id)) {
            is Resource.Error -> {
                WrapperResponse(
                    result = null,
                    error = moviesRes.error
                )
            }

            is Resource.Successful<*> -> {
                val objectResponse: Movie? = moviesRes.data as? Movie
                WrapperResponse(
                    result = objectResponse,
                    error = null
                )
            }
        }
    }
}