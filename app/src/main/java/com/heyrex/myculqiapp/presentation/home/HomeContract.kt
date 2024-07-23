package com.heyrex.myculqiapp.presentation.home

import androidx.annotation.StringRes
import com.heyrex.myculqiapp.domain.model.Friend
import com.heyrex.myculqiapp.domain.model.Profile

data class HomeState(
    val loading: Boolean = false,
    val profile: Profile? = null,
    val friends: List<Friend> = emptyList()
)

sealed class HomeIntent {
    data class GetProfile(val profileId: String) : HomeIntent()
    data object GetFriends : HomeIntent()
}

sealed class HomeEvent {
    data object GetFriends : HomeEvent()
    data class ErrorKey(@StringRes val key: Int) : HomeEvent()
}