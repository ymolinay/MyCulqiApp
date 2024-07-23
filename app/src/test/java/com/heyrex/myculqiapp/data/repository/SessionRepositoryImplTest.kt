package com.heyrex.myculqiapp.data.repository

import com.heyrex.myculqiapp.data.api.response.LoginResponse
import com.heyrex.myculqiapp.data.api.services.ApiService
import com.heyrex.myculqiapp.domain.model.Login
import com.heyrex.myculqiapp.domain.model.toModel
import com.heyrex.myculqiapp.domain.model.toRequest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SessionRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var sessionRepository: SessionRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        sessionRepository = SessionRepositoryImpl(apiService)
    }

    @Test
    fun `login should return success result when api call is successful`() = runTest {
        val loginModel = Login("", "")
        val loginResponse = LoginResponse("")
        val response = mockk<retrofit2.Response<LoginResponse>> {
            every { body() } returns loginResponse
            every { isSuccessful } returns true
        }

        coEvery { apiService.login(loginModel.toRequest()) } returns response

        val result = sessionRepository.login(loginModel).first()

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(loginResponse.toModel(), result.getOrNull())
    }

    @Test
    fun `login should return failure result when api call throws exception`() = runTest {
        val loginModel = Login("", "")
        val exception = RuntimeException("Network error")

        coEvery { apiService.login(loginModel.toRequest()) } throws exception

        val result = sessionRepository.login(loginModel).first()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }
}