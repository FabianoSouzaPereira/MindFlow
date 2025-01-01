package com.fabianospdev.mindflow.features.settings.di

import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsDataSource
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsFirebaseDataSource
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsRemoteDataSource
import com.fabianospdev.mindflow.features.settings.data.repositories.SettingsRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCase
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCaseImpl
import com.fabianospdev.mindflow.features.settings.presentation.viewmodel.SettingsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsDataSource(
        appConfig: AppConfig,
        retrofitDataSource: SettingsRemoteDataSource,
        firebaseDataSource: SettingsFirebaseDataSource
    ): SettingsDataSource {
        return if (appConfig.isUsingFirebase.value) firebaseDataSource else retrofitDataSource
    }

    @Provides
    fun provideSettingsRepository(
        remoteDataSource: SettingsRemoteDataSource
    ): SettingsRemoteRepository {
        return SettingsRemoteRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideSettingsUseCase(
        repository: SettingsRemoteRepository
    ): SettingsRemoteUseCase {
        return SettingsRemoteUseCaseImpl(repository)
    }

    @Provides
    fun providerSettingsViewModel(
        settingsRemoteUseCase: SettingsRemoteUseCase,
        retryController: RetryController,
        appConfig: AppConfig
    ) : SettingsViewModel {
        return SettingsViewModel(useCase = settingsRemoteUseCase, retryController = retryController, appConfig = appConfig)
    }
}