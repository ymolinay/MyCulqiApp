package com.heyrex.myculqiapp.presentation.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Intent> : ViewModel() {

    private val _intents = Channel<Intent>(Channel.BUFFERED)

    abstract var viewState: State
        protected set

    init {
        viewModelScope.launch {
            _intents.consumeEach {
                processIntent(it)
            }
        }
    }

    abstract fun processIntent(intent: Intent)

    open fun sendIntent(intent: Intent) {
        viewModelScope.launch {
            _intents.send(intent)
        }
    }

}

fun <S> ViewModel.launch(flow: Flow<Result<S>>, success: (S) -> Unit, error: (Any) -> Unit) {
    viewModelScope.launch {
        flow.collect { result ->
            result.fold(
                onSuccess = success,
                onFailure = error
            )
        }
    }
}