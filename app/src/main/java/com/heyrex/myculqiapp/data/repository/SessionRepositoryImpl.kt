package com.heyrex.myculqiapp.data.repository

import com.heyrex.myculqiapp.core.utils.safeCall
import com.heyrex.myculqiapp.data.api.services.ApiService
import com.heyrex.myculqiapp.domain.model.Login
import com.heyrex.myculqiapp.domain.model.LoginResult
import com.heyrex.myculqiapp.domain.model.Profile
import com.heyrex.myculqiapp.domain.model.UserRegister
import com.heyrex.myculqiapp.domain.model.UserRegistered
import com.heyrex.myculqiapp.domain.model.toModel
import com.heyrex.myculqiapp.domain.model.toRequest
import com.heyrex.myculqiapp.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SessionRepositoryImpl(
    private val api: ApiService
) : SessionRepository {

    override fun login(login: Login): Flow<Result<LoginResult?>> = flow {
        val request = api.login(login.toRequest())
        val response = request.safeCall({ loginResponse -> loginResponse.toModel() })
        emit(response)
    }.catch {
        it.printStackTrace()
        emit(Result.failure(it))
    }

    override fun register(register: UserRegister): Flow<Result<UserRegistered?>> = flow {
        val request = api.register(register.toRequest())
        val response = request.safeCall({ registerResponse -> registerResponse.toModel() })
        emit(response)
    }.catch {
        it.printStackTrace()
        emit(Result.failure(it))
    }

    override fun profile(profile: String): Flow<Result<Profile?>> = flow {
        val request = api.getProfile("4")
        val response = request.safeCall({ profileResponse -> profileResponse.data.toModel() })
        emit(response)
    }.catch {
        it.printStackTrace()
        emit(Result.failure(it))
    }
}