package com.idaxmx.moviedemo.domain

import com.idaxmx.moviedemo.data.model.GenericMovieResponse
import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.data.repository.MovieRepositoryImp
import com.idaxmx.moviedemo.util.network_response.Resource
import com.idaxmx.moviedemo.util.network_response.WrapperResponse
import javax.inject.Inject

class FetchMovieByTitle @Inject constructor(
    private val repository: MovieRepositoryImp
) {

    suspend operator fun invoke(title: String): WrapperResponse<Set<Movie>> {
        return when (val moviesRes: Resource = repository.getMovieByTitle(title)) {
            is Resource.Error -> {
                WrapperResponse(
                    result = null,
                    error = moviesRes.error
                )
            }

            is Resource.Successful<*> -> {
                val movies = (moviesRes.data as GenericMovieResponse).results ?: emptySet()
                WrapperResponse(
                    result = movies,
                    error = null
                )
            }
        }
    }
}