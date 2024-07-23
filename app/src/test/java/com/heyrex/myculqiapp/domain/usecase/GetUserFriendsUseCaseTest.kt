package com.heyrex.myculqiapp.domain.usecase

import com.heyrex.myculqiapp.domain.repository.FriendRepository
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUserFriendsUseCaseTest {

    lateinit var sut: GetUserFriendsUseCase

    @Mock
    lateinit var friendRepository: FriendRepository

    @Before
    fun setup() {
        initInteractor()
    }

    private fun initInteractor() {
        sut = GetUserFriendsUseCase(friendRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetSampleCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execute() }
        verify(friendRepository, times(1)).getFriends()
    }
}