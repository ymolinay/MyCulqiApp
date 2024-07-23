package com.heyrex.myculqiapp.domain.repository

import com.heyrex.myculqiapp.domain.model.Login
import com.heyrex.myculqiapp.domain.model.LoginResult
import com.heyrex.myculqiapp.domain.model.Profile
import com.heyrex.myculqiapp.domain.model.UserRegister
import com.heyrex.myculqiapp.domain.model.UserRegistered
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    @Throws(Exception::class)
    fun login(login: Login): Flow<Result<LoginResult?>>

    @Throws(Exception::class)
    fun register(register: UserRegister): Flow<Result<UserRegistered?>>

    @Throws(Exception::class)
    fun profile(profile: String): Flow<Result<Profile?>>
}