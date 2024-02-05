package com.idaxmx.moviedemo.ui.movies.home

import com.idaxmx.moviedemo.data.model.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val list: List<Movie> = emptyList(),
    //val searchMovieList: List<Movie> = emptyList(),
    val titleToSearch: String? = null,

    ) {
    fun updateIsLoading(isLoading: Boolean) = copy(isLoading = isLoading)
    fun updateMovieList(list: Set<Movie>) = copy(list = this.list + list)
    fun updateMovieListSearched(list: Set<Movie>) = copy(list = list.toList())
    fun updateTitleToSearch(title: String) = copy(titleToSearch = title)
    fun clearList() = copy(list = emptyList())

    val isSearchAMovie: Boolean get() = list.isEmpty()
    // show = true | false
}