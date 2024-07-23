package com.heyrex.myculqiapp.domain.model

import com.heyrex.myculqiapp.data.api.response.LoginResponse

data class LoginResult(
    val token: String
)

fun LoginResponse.toModel() = LoginResult(token = token)