package com.heyrex.myculqiapp.core.di

import android.app.Application
import android.content.Context
import com.heyrex.myculqiapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    companion object {
        const val nameApp = "moduleName"
    }

    @Provides
    @Singleton
    fun provideApplicationModule(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    @Named(nameApp)
    fun provideModuleName(context: Context): String {
        return context.getString(R.string.app_name)
    }
}