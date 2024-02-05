package com.idaxmx.moviedemo.util.network_response

data class WrapperResponse<T>(
    val result: T? = null,
    val error: Error? = null,
)

data class Error(
    val errorMessage: String = "UnknownError",
    val statusCode: Int = 400,
    val details: String = "Unknown error",
)