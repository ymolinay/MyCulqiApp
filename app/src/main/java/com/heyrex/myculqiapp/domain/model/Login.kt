package com.heyrex.myculqiapp.domain.model

import com.heyrex.myculqiapp.data.api.request.LoginRequest

open class Login(
    val email: String,
    val password: String,
)

fun Login.toRequest() = LoginRequest(email = email, password = password)