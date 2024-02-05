package com.idaxmx.moviedemo.ui.movies.details

import com.idaxmx.moviedemo.data.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
) {
    fun updateIsLoading(isLoading: Boolean) = copy(isLoading = isLoading)
    fun updateMovie(movie: Movie?) = copy(movie = movie)
}