package com.idaxmx.moviedemo.util.network_response

sealed class Resource {
    class Successful<T>(val data: T) : Resource()
    class Error(val error: com.idaxmx.moviedemo.util.network_response.Error) : Resource()
}