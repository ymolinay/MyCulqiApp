package com.heyrex.myculqiapp.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.heyrex.myculqiapp.R
import com.heyrex.myculqiapp.domain.model.UserRegister
import com.heyrex.myculqiapp.domain.usecase.LoginUseCase
import com.heyrex.myculqiapp.domain.usecase.RegisterUserUseCase
import com.heyrex.myculqiapp.presentation.common.viewmodel.BaseViewModel
import com.heyrex.myculqiapp.presentation.common.viewmodel.EventDelegate
import com.heyrex.myculqiapp.presentation.common.viewmodel.EventDelegateViewModel
import com.heyrex.myculqiapp.presentation.common.viewmodel.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
) : BaseViewModel<SignUpState, SignUpIntent>(),
    EventDelegate<SignUpEvent> by EventDelegateViewModel() {

    override var viewState: SignUpState by mutableStateOf(SignUpState())

    override fun processIntent(intent: SignUpIntent) = when (intent) {
        is SignUpIntent.EmailChanged -> onEmailChanged(intent.email)
        is SignUpIntent.NameChanged -> onNameChanged(intent.name)
        is SignUpIntent.PasswordChanged -> onPasswordChanged(intent.password)
        is SignUpIntent.DoSignUp -> onSignUp(intent.email, intent.name, intent.password)
    }

    private fun onEmailChanged(email: String) {
        viewState = viewState.copy(
            email = email
        )
    }

    private fun onNameChanged(name: String) {
        viewState = viewState.copy(
            name = name
        )
    }

    private fun onPasswordChanged(password: String) {
        viewState = viewState.copy(
            password = password
        )
    }

    private fun onSignUp(
        email: String, name: String, password: String
    ) {
        if (validateFields()) {
            val userRegister = UserRegister(email = email, password = password)
            viewState = viewState.copy(loading = true)
            launch(registerUserUseCase.execute(userRegister), {
                viewModelScope.launch {
                    sendEvent(SignUpEvent.NavigateToHome)
                }
            }, {
                viewModelScope.launch {
                    viewState = viewState.copy(loading = false)
                    sendEvent(SignUpEvent.ErrorKey(R.string.common_error_signup))
                }
            })
        }
    }

    private fun validateFields(): Boolean {
        val email = viewState.email
        val name = viewState.name
        val password = viewState.password
        viewState = viewState.copy(
            errorEmail = if (email.isBlank()) R.string.emptyField else null,
            errorName = if (name.isBlank()) R.string.emptyField else null,
            errorPassword = if (password.isBlank()) R.string.emptyField else null,
        )
        return hasValidFields()
    }

    private fun hasValidFields() =
        viewState.errorEmail == null && viewState.errorName == null && viewState.errorPassword == null
}