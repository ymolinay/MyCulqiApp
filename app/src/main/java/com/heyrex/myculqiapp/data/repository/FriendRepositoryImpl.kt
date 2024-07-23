package com.heyrex.myculqiapp.data.repository

import com.heyrex.myculqiapp.core.utils.safeCall
import com.heyrex.myculqiapp.data.api.services.ApiService
import com.heyrex.myculqiapp.domain.model.Friend
import com.heyrex.myculqiapp.domain.model.toModel
import com.heyrex.myculqiapp.domain.repository.FriendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FriendRepositoryImpl(
    private val api: ApiService
) : FriendRepository {

    override fun getFriends(): Flow<Result<List<Friend>?>> = flow {
        val request = api.getFriends()
        val response = request.safeCall({ friendsResponse ->
            friendsResponse.data.map { it.toModel() } ?: emptyList()
        })
        emit(response)
    }.catch {
        it.printStackTrace()
        emit(Result.failure(it))
    }
}