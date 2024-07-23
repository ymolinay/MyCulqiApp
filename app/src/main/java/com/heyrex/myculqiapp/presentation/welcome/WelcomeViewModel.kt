package com.heyrex.myculqiapp.presentation.welcome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.heyrex.myculqiapp.R
import com.heyrex.myculqiapp.core.utils.isValidEmail
import com.heyrex.myculqiapp.presentation.common.viewmodel.BaseViewModel
import com.heyrex.myculqiapp.presentation.common.viewmodel.EventDelegate
import com.heyrex.myculqiapp.presentation.common.viewmodel.EventDelegateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
) : BaseViewModel<WelcomeState, WelcomeIntent>(),
    EventDelegate<WelcomeEvent> by EventDelegateViewModel() {

    override var viewState by mutableStateOf(WelcomeState())

    override fun processIntent(intent: WelcomeIntent) = when (intent) {
        is WelcomeIntent.EmailChanged -> onEmailChanged(intent.email)
        is WelcomeIntent.DoContinue -> onContinue(intent.email)
        is WelcomeIntent.DoSignUp -> onSignUp(intent.email)
    }

    private fun onEmailChanged(email: String) {
        viewState = viewState.copy(
            email = email
        )
    }

    private fun onContinue(email: String) {
        if (validateFields()) {
            viewState = viewState.copy(loading = true)
            viewModelScope.launch {
                viewState = viewState.copy(loading = false)
                sendEvent(WelcomeEvent.NavigateToLogin(email))
            }
        }
    }

    private fun onSignUp(email: String) {
        if (validateFields()) {
            viewState = viewState.copy(loading = true)
            viewModelScope.launch {
                viewState = viewState.copy(loading = false)
                sendEvent(WelcomeEvent.NavigateToSignUp(email))
            }
        }
    }

    private fun validateFields(): Boolean {
        val email = viewState.email
        viewState = viewState.copy(
            errorEmail = when {
                email.isBlank() -> R.string.emptyField
                !email.isValidEmail() -> R.string.emailInvalid
                else -> null
            },
        )
        return hasValidFields()
    }

    fun hasValidFields() =
        viewState.errorEmail == null
}