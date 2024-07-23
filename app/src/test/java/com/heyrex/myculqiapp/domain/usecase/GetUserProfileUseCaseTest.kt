package com.heyrex.myculqiapp.domain.usecase

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
class GetUserProfileUseCaseTest {

    lateinit var sut: GetUserProfileUseCase

    @Mock
    lateinit var sessionRepository: SessionRepository

    @Before
    fun setup() {
        initInteractor()
    }

    private var profileId: String = ""
    private fun initInteractor() {
        sut = GetUserProfileUseCase(sessionRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetSampleCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execute(profileId) }
        verify(sessionRepository, times(1)).profile(profileId)
    }
}