package com.heyrex.myculqiapp.presentation.welcome

import androidx.annotation.StringRes

data class WelcomeState(
    val loading: Boolean = false,
    val email: String = "eve.holt@reqres.in",
    @StringRes val errorEmail: Int? = null,
)

sealed class WelcomeIntent {
    class EmailChanged(val email: String) : WelcomeIntent()
    class DoContinue(val email: String) : WelcomeIntent()
    class DoSignUp(val email: String) : WelcomeIntent()
}

sealed class WelcomeEvent {
    data class NavigateToLogin(val email: String) : WelcomeEvent()
    data class NavigateToSignUp(val email: String) : WelcomeEvent()
    data class ErrorKey(@StringRes val key: Int) : WelcomeEvent()
}