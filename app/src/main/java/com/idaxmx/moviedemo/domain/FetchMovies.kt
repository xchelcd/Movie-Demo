package com.idaxmx.moviedemo.domain

import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.data.model.NowPlayingResponse
import com.idaxmx.moviedemo.data.repository.MovieRepository
import com.idaxmx.moviedemo.util.network_response.Resource
import com.idaxmx.moviedemo.util.network_response.WrapperResponse
import javax.inject.Inject

class FetchMovies @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): WrapperResponse<List<Movie>> {
        return when (val moviesRes: Resource = repository.getMovies()) {
            is Resource.Error -> {
                WrapperResponse(
                    result = null,
                    error = moviesRes.error
                )
            }

            is Resource.Successful<*> -> {
                val objectResponse = moviesRes.data as NowPlayingResponse
                val page = objectResponse.page
                val movies = moviesRes.data.results ?: emptyList()
                WrapperResponse(
                    result = movies,
                    error = null
                )
            }
        }
    }
}