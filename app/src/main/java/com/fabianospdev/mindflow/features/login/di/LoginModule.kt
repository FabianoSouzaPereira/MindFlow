package com.fabianospdev.mindflow.features.login.di

import android.content.Context
import com.fabianospdev.mindflow.core.database.dao.UserDao
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.login.data.datasources.remoto.LoginDataSource
import com.fabianospdev.mindflow.features.login.data.datasources.remoto.LoginFirebaseDataSource
import com.fabianospdev.mindflow.features.login.data.datasources.remoto.LoginRemoteDataSource
import com.fabianospdev.mindflow.features.login.data.repositories.LoginRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginRemoteRepository
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCase
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCaseImpl
import com.fabianospdev.mindflow.features.login.presentation.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideLoginDataSource(
        appConfig: AppConfig,
        retrofitDataSource: LoginRemoteDataSource,
        firebaseDataSource: LoginFirebaseDataSource
    ): LoginDataSource {
        return if (appConfig.isUsingFirebase.value) firebaseDataSource else retrofitDataSource
    }

    @Provides
    fun provideLoginRepository(
        dataSource: LoginDataSource,
        userDao: UserDao,
        @ApplicationContext context: Context
    ): LoginRemoteRepository {
        return LoginRemoteRepositoryImpl(dataSource, userDao, context)
    }

    @Provides
    fun provideLoginUseCase(
        repository: LoginRemoteRepository
    ): LoginRemoteUseCase {
        return LoginRemoteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideLoginRemoteDataSource(retrofit: Retrofit): LoginRemoteDataSource {
        val loginDataSource = retrofit.create(LoginDataSource::class.java)
        return LoginRemoteDataSource(loginDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginFirebaseDataSource(
        appConfig: AppConfig,
        firebaseAuth: FirebaseAuth
    ): LoginFirebaseDataSource {
        return LoginFirebaseDataSource(appConfig, firebaseAuth)
    }

    @Provides
    fun providerLoginViewModel(
        loginRemoteUsecase: LoginRemoteUseCase,
        retryController: RetryController,
        appConfig: AppConfig
    ): LoginViewModel {
        return LoginViewModel(useCase = loginRemoteUsecase, retryController = retryController, appConfig = appConfig)
    }
}
