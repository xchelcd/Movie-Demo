package com.idaxmx.moviedemo.ui.movies.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.data.model.NowPlayingResponse
import com.idaxmx.moviedemo.domain.FetchMovies
import com.idaxmx.moviedemo.util.Const
import com.idaxmx.moviedemo.util.network_response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMovies: FetchMovies
): ViewModel() {

    private var _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state

    init {
        fetchListMovieList()
    }

    private fun fetchListMovieList() = viewModelScope.launch {
        try {
            setIsLoading(true)

            val movieListRes = fetchMovies()
            if (movieListRes.error == null) {
                setMovieList(movieListRes.result!!)
            } else {
                val error = movieListRes.error
            }
        } finally {
            setIsLoading(false)
        }
    }

    private fun setMovieList(list: List<Movie>) {
        _state.update { it.updateMovieList(list) }
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
}