package com.fabianospdev.mindflow.features.settings.di

import com.fabianospdev.mindflow.core.di.CoreModule
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsApi
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsDataSource
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsFirebaseDataSourceImpl
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsRemoteDataSourceImpl
import com.fabianospdev.mindflow.features.settings.data.repositories.SettingsRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCase
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCaseImpl
import com.fabianospdev.mindflow.features.settings.presentation.viewmodel.SettingsViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsApiDataSource(
        appConfig: AppConfig,
        retrofitDataSource: SettingsRemoteDataSourceImpl
    ): SettingsApi {
        return SettingsRemoteDataSourceImpl(retrofitDataSource)
    }

    @Provides
    @Singleton
    fun provideSettingsDataSource(
        appConfig: AppConfig,
        firestore: FirebaseFirestore
    ): SettingsDataSource {
        return SettingsFirebaseDataSourceImpl(firestore)
    }


    @Provides
    fun provideSettingsRepository(
        appConfig: AppConfig,
        settingsApi: SettingsApi,
        remoteDataSource: SettingsDataSource
    ): SettingsRemoteRepository {
        return SettingsRemoteRepositoryImpl(appConfig, settingsApi, remoteDataSource)
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
    ): SettingsViewModel {
        return SettingsViewModel(useCase = settingsRemoteUseCase, retryController = retryController, appConfig = appConfig)
    }

    @Provides
    @Singleton
    fun provideSettingsRemoteDataSource(retrofit: Retrofit): SettingsRemoteDataSourceImpl {
        val settingsApi = retrofit.create(SettingsApi::class.java)
        return SettingsRemoteDataSourceImpl(settingsApi)
    }

    @Provides
    @Singleton
    @CoreModule.FirebaseSource
    fun provideSettingsFirebaseDataSource(firebaseFirestore: FirebaseFirestore): SettingsFirebaseDataSourceImpl {
        return SettingsFirebaseDataSourceImpl(firebaseFirestore)
    }
}