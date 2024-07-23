package com.heyrex.myculqiapp.domain.usecase

import com.heyrex.myculqiapp.domain.repository.FriendRepository
import javax.inject.Inject

class GetUserFriendsUseCase
@Inject constructor(private val friendRepository: FriendRepository) {
    fun execute() = friendRepository.getFriends()
}