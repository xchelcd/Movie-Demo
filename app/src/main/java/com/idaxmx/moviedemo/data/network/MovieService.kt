package com.idaxmx.moviedemo.data.network

import android.util.Log
import com.idaxmx.moviedemo.util.network_response.Error
import com.idaxmx.moviedemo.util.network_response.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieApi
) {

    suspend fun getMovies(page: Int): Resource = withContext(IO) {
        Log.d("MovieService", "page:$page")
        val movieListResponse = api.getMovies(page)

        if (movieListResponse.isSuccessful) {
            Log.d(
                "MovieService",
                "ids: ${movieListResponse.body()?.results?.joinToString { it.id.toString() }}"
            )
            Resource.Successful(movieListResponse.body())
        } else {
            Resource.Error(
                Error(
                    errorMessage = "",
                    statusCode = movieListResponse.code(),
                    details = ""
                )
            )
        }
    }

    suspend fun getMovieById(id: Long): Resource = withContext(IO) {
        val movieResponse = api.getMovieById(id)

        if (movieResponse.isSuccessful) {
            Resource.Successful(movieResponse.body())
        } else {
            Resource.Error(
                Error(
                    errorMessage = "",
                    statusCode = movieResponse.code(),
                    details = ""
                )
            )
        }
    }

}