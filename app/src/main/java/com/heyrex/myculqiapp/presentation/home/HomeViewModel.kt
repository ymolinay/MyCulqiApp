package com.heyrex.myculqiapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.heyrex.myculqiapp.R
import com.heyrex.myculqiapp.domain.usecase.GetUserFriendsUseCase
import com.heyrex.myculqiapp.domain.usecase.GetUserProfileUseCase
import com.heyrex.myculqiapp.presentation.common.viewmodel.BaseViewModel
import com.heyrex.myculqiapp.presentation.common.viewmodel.EventDelegate
import com.heyrex.myculqiapp.presentation.common.viewmodel.EventDelegateViewModel
import com.heyrex.myculqiapp.presentation.common.viewmodel.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserFriendsUseCase: GetUserFriendsUseCase,
) : BaseViewModel<HomeState, HomeIntent>(),
    EventDelegate<HomeEvent> by EventDelegateViewModel() {

    override var viewState by mutableStateOf(HomeState())

    override fun processIntent(intent: HomeIntent) = when (intent) {
        is HomeIntent.GetProfile -> getProfile(intent.profileId)
        is HomeIntent.GetFriends -> getFriends()
    }

    private fun getProfile(profileId: String) {
        viewState = viewState.copy(loading = true)
        launch(getUserProfileUseCase.execute(profileId), { profile ->
            viewModelScope.launch(Dispatchers.IO) {
                viewState = viewState.copy(
                    profile = profile
                )
                sendEvent(HomeEvent.GetFriends)
            }
        }, {
            viewModelScope.launch {
                viewState = viewState.copy(loading = false)
                sendEvent(HomeEvent.ErrorKey(R.string.common_error))
            }
        })
    }

    private fun getFriends() {
        viewState = viewState.copy(loading = true)
        launch(getUserFriendsUseCase.execute(), { friends ->
            viewModelScope.launch {
                viewState = viewState.copy(
                    loading = false,
                    friends = friends ?: emptyList()
                )
            }
        }, {
            viewModelScope.launch {
                viewState = viewState.copy(loading = false)
                sendEvent(HomeEvent.ErrorKey(R.string.common_error))
            }
        })
    }
}