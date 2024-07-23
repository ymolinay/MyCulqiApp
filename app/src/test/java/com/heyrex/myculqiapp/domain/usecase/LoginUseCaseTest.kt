package com.heyrex.myculqiapp.domain.usecase

import com.heyrex.myculqiapp.domain.model.Login
import com.heyrex.myculqiapp.domain.repository.SessionRepository
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    lateinit var sut: LoginUseCase

    @Mock
    lateinit var sessionRepository: SessionRepository

    @Before
    fun setup() {
        initInteractor()
    }

    @Mock
    lateinit var loginModel: Login
    private fun initInteractor() {
        sut = LoginUseCase(sessionRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetSampleCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execute(loginModel) }
        verify(sessionRepository, times(1)).login(loginModel)
    }
}