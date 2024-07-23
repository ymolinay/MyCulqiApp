package com.heyrex.myculqiapp.data.api.services

import com.heyrex.myculqiapp.data.api.request.LoginRequest
import com.heyrex.myculqiapp.data.api.request.RegisterRequest
import com.heyrex.myculqiapp.data.api.response.FriendsResponse
import com.heyrex.myculqiapp.data.api.response.LoginResponse
import com.heyrex.myculqiapp.data.api.response.ProfileResponse
import com.heyrex.myculqiapp.data.api.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    companion object {
        //Headers
        const val CONTENT_TYPE = "Content-Type: application/json"
        const val ACCEPT = "Accept: application/json"
    }

    @Headers(CONTENT_TYPE, ACCEPT)
    @POST(CulqiUrls.API + CulqiUrls.LOGIN)
    suspend fun login(@Body login: LoginRequest): Response<LoginResponse>

    @Headers(CONTENT_TYPE, ACCEPT)
    @POST(CulqiUrls.API + CulqiUrls.REGISTER)
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @Headers(CONTENT_TYPE, ACCEPT)
    @GET(CulqiUrls.API + CulqiUrls.PROFILE)
    suspend fun getProfile(@Path("profileId") profileId: String): Response<ProfileResponse>

    @Headers(CONTENT_TYPE, ACCEPT)
    @GET(CulqiUrls.API + CulqiUrls.FRIENDS)
    suspend fun getFriends(): Response<FriendsResponse>
}