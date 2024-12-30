package com.fabianospdev.mindflow.core.di

import com.fabianospdev.mindflow.core.helpers.DefaultRetryController
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.login.data.datasources.LoginRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginRemoteDataSource(retrofit: Retrofit): LoginRemoteDataSource {
        return retrofit.create(LoginRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideRetryController(): RetryController {
        return DefaultRetryController(maxRetries = 3)
    }
}
