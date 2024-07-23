package com.heyrex.myculqiapp.core.di

import com.heyrex.myculqiapp.data.api.services.ApiService
import com.heyrex.myculqiapp.data.repository.FriendRepositoryImpl
import com.heyrex.myculqiapp.data.repository.SessionRepositoryImpl
import com.heyrex.myculqiapp.domain.repository.FriendRepository
import com.heyrex.myculqiapp.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSessionRepository(
        api: ApiService,
    ): SessionRepository {
        return SessionRepositoryImpl(
            api = api,
        )
    }

    @Provides
    @Singleton
    fun provideFriendRepository(
        api: ApiService,
    ): FriendRepository {
        return FriendRepositoryImpl(
            api = api,
        )
    }
}