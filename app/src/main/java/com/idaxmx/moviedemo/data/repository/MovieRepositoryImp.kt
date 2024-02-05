package com.idaxmx.moviedemo.data.repository

import com.idaxmx.moviedemo.data.network.MovieService
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val service: MovieService
): MovieRepository {

    override suspend fun getMovies(page: Int) = service.getMovies(page)

    override suspend fun getMovieById(id: Long) = service.getMovieById(id)

    override suspend fun getMovieByTitle(title: String) = service.getMovieByTitle(title)
}