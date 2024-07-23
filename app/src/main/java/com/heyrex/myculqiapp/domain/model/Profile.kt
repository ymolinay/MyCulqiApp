package com.heyrex.myculqiapp.domain.model

import com.heyrex.myculqiapp.data.api.response.ProfileDataResponse

data class Profile(
    val id: String,
    val email: String,
    val name: String,
    val last: String,
    val avatar: String,
)

fun ProfileDataResponse.toModel() = Profile(
    id = id,
    email = email,
    name = firstName,
    last = lastName,
    avatar = avatar,
)