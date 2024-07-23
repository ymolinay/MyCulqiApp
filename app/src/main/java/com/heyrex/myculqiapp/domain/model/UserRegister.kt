package com.heyrex.myculqiapp.domain.model

import com.heyrex.myculqiapp.data.api.request.RegisterRequest

open class UserRegister(
    val email: String,
    val password: String,
)

fun UserRegister.toRequest() = RegisterRequest(email = email, password = password)