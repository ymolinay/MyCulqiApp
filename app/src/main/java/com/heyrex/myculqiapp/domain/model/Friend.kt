package com.heyrex.myculqiapp.domain.model

import com.heyrex.myculqiapp.data.api.response.FriendDataResponse

data class Friend(
    val id: String,
    val email: String,
    val name: String,
    val last: String,
    val avatar: String,
)

fun FriendDataResponse.toModel() = Friend(
    id = id,
    email = email,
    name = firstName,
    last = lastName,
    avatar = avatar,
)