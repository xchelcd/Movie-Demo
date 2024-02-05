package com.idaxmx.moviedemo.data.repository

import com.idaxmx.moviedemo.util.network_response.Resource

interface LoginRepository {
    suspend fun login(username: String, password: String): Resource
}