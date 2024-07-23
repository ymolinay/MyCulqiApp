package com.heyrex.myculqiapp.domain.repository

import com.heyrex.myculqiapp.domain.model.Friend
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    @Throws(Exception::class)
    fun getFriends(): Flow<Result<List<Friend>?>>
}