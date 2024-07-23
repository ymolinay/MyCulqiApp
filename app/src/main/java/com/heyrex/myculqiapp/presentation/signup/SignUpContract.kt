package com.heyrex.myculqiapp.presentation.signup

import androidx.annotation.StringRes

data class SignUpState(
    val loading: Boolean = false,
    val email: String = "",
    val name: String = "",
    val password: String = "",
    @StringRes val errorEmail: Int? = null,
    @StringRes val errorName: Int? = null,
    @StringRes val errorPassword: Int? = null,
    @StringRes val errorSignUp: Int? = null,
)

sealed class SignUpIntent {
    class EmailChanged(val email: String) : SignUpIntent()
    class NameChanged(val name: String) : SignUpIntent()
    class PasswordChanged(val password: String) : SignUpIntent()
    class DoSignUp(val email: String, val name: String, val password: String) : SignUpIntent()
}

sealed class SignUpEvent {
    data object NavigateToHome : SignUpEvent()
    data class ErrorKey(@StringRes val key: Int) : SignUpEvent()
}