package com.idaxmx.moviedemo.ui.movies.home

import com.idaxmx.moviedemo.data.model.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val list: List<Movie> = emptyList(),
) {
    fun updateIsLoading(isLoading: Boolean) = copy(isLoading = isLoading)
    fun updateMovieList(list: List<Movie>) = copy(list = list)
}