package com.idaxmx.moviedemo.ui.movies.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.domain.FetchMovieById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val fetchMovieById: FetchMovieById
) : ViewModel() {

    private var _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> get() = _state

    fun fetchMovie(id: Long) = viewModelScope.launch {
        try {
            setLoading(true)
            val movieRes = fetchMovieById(id)
            if (movieRes.error == null) {
                setMovie(movieRes.result!!)
            } else {
                val error = movieRes.error
            }
        } finally {
            setLoading(false)
        }
    }

    private fun setMovie(movie: Movie) {
        _state.update { it.updateMovie(movie) }
    }

    private fun setLoading(isLoading: Boolean) {
        _state.update { it.updateIsLoading(isLoading) }
    }
}