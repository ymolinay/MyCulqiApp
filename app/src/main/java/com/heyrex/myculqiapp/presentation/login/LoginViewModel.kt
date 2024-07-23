package com.heyrex.myculqiapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.heyrex.myculqiapp.R
import com.heyrex.myculqiapp.domain.model.Login
import com.heyrex.myculqiapp.domain.usecase.LoginUseCase
import com.heyrex.myculqiapp.presentation.common.viewmodel.BaseViewModel
import com.heyrex.myculqiapp.presentation.common.viewmodel.EventDelegate
import com.heyrex.myculqiapp.presentation.common.viewmodel.EventDelegateViewModel
import com.heyrex.myculqiapp.presentation.common.viewmodel.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginIntent>(),
    EventDelegate<LoginEvent> by EventDelegateViewModel() {

    override var viewState by mutableStateOf(LoginState())

    override fun processIntent(intent: LoginIntent) = when (intent) {
        is LoginIntent.EmailChanged -> onEmailChanged(intent.email)
        is LoginIntent.PasswordChanged -> onPasswordChanged(intent.password)
        is LoginIntent.DoLogin -> onLogin(intent.email, intent.password)
    }

    private fun onEmailChanged(email: String) {
        viewState = viewState.copy(
            email = email
        )
    }

    private fun onPasswordChanged(password: String) {
        viewState = viewState.copy(
            password = password
        )
    }

    private fun onLogin(
        email: String, password: String
    ) {
        if (validateFields()) {
            val loginModel = Login(email = email, password = password)
            viewState = viewState.copy(loading = true)
            launch(loginUseCase.execute(loginModel), {
                viewModelScope.launch {
                    sendEvent(LoginEvent.NavigateToHome)
                }
            }, {
                viewModelScope.launch {
                    viewState = viewState.copy(loading = false)
                    sendEvent(LoginEvent.ErrorKey(R.string.common_error_login))
                }
            })
        }
    }

    private fun validateFields(): Boolean {
        val email = viewState.email
        val password = viewState.password
        viewState = viewState.copy(
            errorEmail = if (email.isBlank()) R.string.emptyField else null,
            errorPassword = if (password.isBlank()) R.string.emptyField else null,
        )
        return hasValidFields()
    }

    private fun hasValidFields() =
        viewState.errorEmail == null && viewState.errorPassword == null
}