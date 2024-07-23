package com.heyrex.myculqiapp.data.repository

import com.heyrex.myculqiapp.data.api.response.FriendsResponse
import com.heyrex.myculqiapp.data.api.response.FriendDataResponse
import com.heyrex.myculqiapp.data.api.services.ApiService
import com.heyrex.myculqiapp.domain.model.toModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class FriendRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var friendRepository: FriendRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        friendRepository = FriendRepositoryImpl(apiService)
    }

    @Test
    fun `getFriends should return success result when api call is successful`() = runTest {
        val friendDataResponse =
            FriendDataResponse("1", "john.doe@example.com", "John", "Doe", "avatar_url")
        val friendsResponse = FriendsResponse(listOf(friendDataResponse))
        val response = mockk<Response<FriendsResponse>> {
            every { body() } returns friendsResponse
            every { isSuccessful } returns true
        }

        coEvery { apiService.getFriends() } returns response

        val result = friendRepository.getFriends().first()

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(friendsResponse.data.map { it.toModel() }, result.getOrNull())
    }

    @Test
    fun `getFriends should return failure result when api call throws exception`() = runTest {
        val exception = RuntimeException("Network error")

        coEvery { apiService.getFriends() } throws exception

        val result = friendRepository.getFriends().first()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }
}