package com.fabianospdev.mindflow.features.login.di

import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.login.data.datasources.LoginRemoteDataSource
import com.fabianospdev.mindflow.features.login.data.repositories.LoginRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginRemoteRepository
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCase
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCaseImpl
import com.fabianospdev.mindflow.features.login.presentation.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideLoginRepository(
        remoteDataSource: LoginRemoteDataSource
    ): LoginRemoteRepository {
        return LoginRemoteRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideLoginUseCase(
        repository: LoginRemoteRepository
    ): LoginRemoteUseCase {
        return LoginRemoteUseCaseImpl(repository)
    }

    @Provides
    fun providerLoginViewModel(
        loginRemoteUsecase: LoginRemoteUseCase,
        retryController: RetryController,
        appConfig: AppConfig
    ) : LoginViewModel {
        return LoginViewModel(useCase = loginRemoteUsecase, retryController = retryController, appConfig = appConfig)
    }
}
