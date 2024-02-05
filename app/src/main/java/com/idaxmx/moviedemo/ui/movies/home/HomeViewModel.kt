package com.idaxmx.moviedemo.ui.movies.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.domain.FetchMovieByTitle
import com.idaxmx.moviedemo.domain.FetchMovies
import com.idaxmx.moviedemo.util.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMovies: FetchMovies,
    private val searchMovie: FetchMovieByTitle
): ViewModel() {

    private var _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state

    init {
        fetchMovieList()
    }


    private var endlessActivated = true

    private var page = 1
    private fun fetchMovieList() = viewModelScope.launch {
        try {
            setIsLoading(true)
            val movieListRes = fetchMovies(page)
            if (movieListRes.error == null) {
                setMovieList(movieListRes.result?.first!!)
                page = movieListRes.result.second
            } else {
                val error = movieListRes.error
            }
        } finally {
            setIsLoading(false)
        }
    }

    fun searchMovie() = viewModelScope.launch {
        page = 1
        val title = state.value.titleToSearch
        Log.d("HomeViewModel", "title:$title")
        if (title.isNullOrBlank()) {
            clearList()
            endlessActivated = true
            fetchMovieList()
            return@launch
        }
        endlessActivated = false
        try {
            setIsLoading(true)
            val movieSearchRes = searchMovie(title)
            if (movieSearchRes.error == null) {
                setMovieSearchedList(movieSearchRes.result!!)
            } else {
                val error = movieSearchRes.error
            }
        } finally {
            setIsLoading(false)
        }
    }

    private fun setMovieList(list: Set<Movie>) {
        _state.update { it.updateMovieList(list) }
    }

    private fun clearList() {
        _state.update { it.clearList() }
    }

    private fun setMovieSearchedList(list: Set<Movie>) {
        _state.update { it.updateMovieListSearched(list) }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.update { it.updateIsLoading(isLoading) }
    }

    fun deleteUser(context: Context) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putBoolean(Const.USER_LOGGED_KEY, false)
        editor.apply()
    }

    fun loadMoreData() = viewModelScope.launch {
        Log.d("HomeViewModel", "page:$page - isEndlessActivated:$endlessActivated")
        if (!endlessActivated) return@launch
        val movieListRes = fetchMovies(page + 1)
        if (movieListRes.error == null) {
            setMovieList(movieListRes.result?.first!!)
            page = movieListRes.result.second
        } else {
            val error = movieListRes.error
        }
    }


    fun setTitleToSearch(title: String) {
        _state.update { it.updateTitleToSearch(title) }
    }
}