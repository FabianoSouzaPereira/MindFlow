package com.fabianospdev.mindflow.features.login.di

import com.fabianospdev.mindflow.features.login.data.datasources.LoginRemoteDataSource
import com.fabianospdev.mindflow.features.login.data.repositories.LoginRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginRemoteRepository
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCase
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCaseImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides


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
}
