package com.idaxmx.moviedemo.ui.login

data class LoginState(
    val isLoading: Boolean = false,
    val nextView: Boolean = false
) {

    fun updateIsLoading(isLoading: Boolean) = copy(isLoading = isLoading)
}