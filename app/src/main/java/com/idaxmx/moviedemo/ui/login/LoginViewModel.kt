package com.idaxmx.moviedemo.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.idaxmx.moviedemo.domain.Login
import com.idaxmx.moviedemo.domain.isValidEmail
import com.idaxmx.moviedemo.util.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: Login,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    private var username: String? = null
    private var password: String? = null


    fun submitLogin(doLogin: () -> Unit) = viewModelScope.launch {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            return@launch
        }
        if (!username!!.isValidEmail()) return@launch
        if (password!!.length < 6) return@launch
        try {
            setIsLoading(true)
            val response = login(username!!, password!!)
            if (response.error == null) {
                doLogin()
            }
        } finally {
            setIsLoading(false)
        }
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun setUsername(username: String) {
        this.username = username
    }


    private fun setIsLoading(isLoading: Boolean) {
        _state.update { it.updateIsLoading(isLoading) }
    }

    fun saveUserState(context: Context) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putBoolean(Const.USER_LOGGED_KEY, true)
        editor.apply()
    }
}