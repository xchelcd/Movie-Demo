package com.idaxmx.moviedemo.domain

import com.idaxmx.moviedemo.data.repository.LoginCredentialsRepositoryImp
import com.idaxmx.moviedemo.util.network_response.Resource
import com.idaxmx.moviedemo.util.network_response.WrapperResponse
import javax.inject.Inject

class Login @Inject constructor(
    private val loginRepository: LoginCredentialsRepositoryImp
) {

    suspend operator fun invoke(username: String, password: String): WrapperResponse<Boolean> {

        return when(val loginRes = loginRepository.login(username, password)) {
            is Resource.Error -> {
                WrapperResponse(
                    error = loginRes.error
                )
            }
            is Resource.Successful<*> -> {
                WrapperResponse(
                    result = true
                )
            }
        }

    }
}