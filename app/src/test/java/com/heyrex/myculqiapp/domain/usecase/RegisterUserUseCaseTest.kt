package com.heyrex.myculqiapp.domain.usecase

import com.heyrex.myculqiapp.domain.model.UserRegister
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
class RegisterUserUseCaseTest {

    lateinit var sut: RegisterUserUseCase

    @Mock
    lateinit var sessionRepository: SessionRepository

    @Before
    fun setup() {
        initInteractor()
    }

    @Mock
    lateinit var userRegister: UserRegister
    private fun initInteractor() {
        sut = RegisterUserUseCase(sessionRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetSampleCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execute(userRegister) }
        verify(sessionRepository, times(1)).register(userRegister)
    }
}