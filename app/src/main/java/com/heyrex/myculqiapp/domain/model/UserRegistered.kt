package com.heyrex.myculqiapp.domain.model

import com.heyrex.myculqiapp.data.api.response.RegisterResponse

data class UserRegistered(
    val id: String,
    val token: String,
)

fun RegisterResponse.toModel() = UserRegistered(id = id, token = token)