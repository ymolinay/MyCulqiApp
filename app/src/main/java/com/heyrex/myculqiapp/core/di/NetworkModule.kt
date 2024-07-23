package com.heyrex.myculqiapp.core.di

import android.app.Application
import com.heyrex.myculqiapp.BuildConfig
import com.heyrex.myculqiapp.R
import com.heyrex.myculqiapp.data.api.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("socketTimeout")
    fun provideSocketTimeout(context: Application): Int {
        return context.resources.getInteger(R.integer.socketTimeout)
    }

    @Provides
    @Singleton
    @Named("connectionTimeout")
    fun provideConnectionTimeout(context: Application): Int {
        return context.resources.getInteger(R.integer.connectionTimeout)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BuildConfig.LEVEL_LOGS
        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named("socketTimeout") socketTimeout: Int,
        @Named("connectionTimeout") connectionTimeout: Int
    ): OkHttpClient {
        val clientBuilder = OkHttpClient().newBuilder()
        clientBuilder.readTimeout(socketTimeout.toLong(), TimeUnit.SECONDS)
        clientBuilder.connectTimeout(connectionTimeout.toLong(), TimeUnit.SECONDS)
        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    @Named("Content")
    internal fun provideContentsRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideContentApiService(@Named("Content") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}