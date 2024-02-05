package com.idaxmx.moviedemo.data.repository

import com.idaxmx.moviedemo.util.network_response.Resource

interface MovieRepository {

    suspend fun getMovies(): Resource
    suspend fun getMovieById(id: Long): Resource
}