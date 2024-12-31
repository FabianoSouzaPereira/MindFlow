package com.fabianospdev.mindflow.features.home.di

import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.home.data.datasources.HomeDataSource
import com.fabianospdev.mindflow.features.home.data.datasources.HomeFirebaseDataSource
import com.fabianospdev.mindflow.features.home.data.datasources.HomeRemoteDataSource
import com.fabianospdev.mindflow.features.home.data.repositories.HomeRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.home.domain.repositories.HomeRemoteRepository
import com.fabianospdev.mindflow.features.home.domain.usecases.HomeRemoteUseCase
import com.fabianospdev.mindflow.features.home.domain.usecases.HomeRemoteUseCaseImpl
import com.fabianospdev.mindflow.features.home.presentation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeDataSource(
        appConfig: AppConfig,
        retrofitDataSource: HomeRemoteDataSource,
        firebaseDataSource: HomeFirebaseDataSource
    ): HomeDataSource {
        return if (appConfig.isUsingFirebase) {
            firebaseDataSource
        } else {
            retrofitDataSource
        }
    }

    @Provides
    fun provideHomeRepository(
        remoteDataSource: HomeDataSource
    ): HomeRemoteRepository {
        return HomeRemoteRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideHomeUseCase(
        repository: HomeRemoteRepository
    ): HomeRemoteUseCase {
        return HomeRemoteUseCaseImpl(repository)
    }

    @Provides
    fun providerHomeViewModel(
        homeRemoteUseCase: HomeRemoteUseCase,
        retryController: RetryController,
        appConfig: AppConfig
    ) : HomeViewModel {
        return HomeViewModel(useCase = homeRemoteUseCase, retryController = retryController, appConfig = appConfig)
    }
}