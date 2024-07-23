package com.heyrex.myculqiapp.presentation.login

import androidx.annotation.StringRes

data class LoginState(
    val loading: Boolean = false,
    val email: String = "",
    val password: String = "",
    @StringRes val errorEmail: Int? = null,
    @StringRes val errorPassword: Int? = null,
    @StringRes val errorLogin: Int? = null,
)

sealed class LoginIntent {
    class EmailChanged(val email: String) : LoginIntent()
    class PasswordChanged(val password: String) : LoginIntent()
    class DoLogin(val email: String, val password: String) : LoginIntent()
}

sealed class LoginEvent {
    data object NavigateToHome : LoginEvent()
    data class ErrorKey(@StringRes val key: Int) : LoginEvent()
}