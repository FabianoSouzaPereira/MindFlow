package com.fabianospdev.mindflow.core.di

import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.DefaultRetryController
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.login.data.datasources.LoginDataSource
import com.fabianospdev.mindflow.features.login.data.datasources.LoginFirebaseDataSource
import com.fabianospdev.mindflow.features.login.data.datasources.LoginRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
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
    fun provideLoginFirebaseDataSource(firebaseAuth: FirebaseAuth): LoginFirebaseDataSource {
        return LoginFirebaseDataSource(firebaseAuth)
    }


    @Provides
    @Singleton
    fun provideRetryController(): RetryController {
        return DefaultRetryController(maxRetries = 3)
    }

    @Provides
    @Singleton
    fun provideLoginDataSource(
        isUsingFirebase: Boolean,
        retrofitDataSource: LoginRemoteDataSource,
        firebaseDataSource: LoginFirebaseDataSource
    ): LoginDataSource {
        return if (isUsingFirebase) {
            firebaseDataSource
        } else {
            retrofitDataSource
        }
    }

    @Provides
    @Singleton
    fun provideAppConfig(): AppConfig {
        return AppConfig(initialState = false)
    }
}
