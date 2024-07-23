package com.heyrex.myculqiapp.data.api.response

import com.google.gson.annotations.SerializedName

data class FriendsResponse(
    @SerializedName("data")
    val data: List<FriendDataResponse>,
)

data class FriendDataResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String,
)