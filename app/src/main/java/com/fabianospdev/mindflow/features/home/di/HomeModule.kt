package com.fabianospdev.mindflow.features.home.di

import android.content.Context
import com.fabianospdev.mindflow.core.di.CoreModule
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
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
        return if (appConfig.isUsingFirebase.value) firebaseDataSource else retrofitDataSource
    }

    @Provides
    fun provideHomeRepository(
        remoteDataSource: HomeDataSource,
        @ApplicationContext context: Context
    ): HomeRemoteRepository {
        return HomeRemoteRepositoryImpl(remoteDataSource, context)
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
    ): HomeViewModel {
        return HomeViewModel(useCase = homeRemoteUseCase, retryController = retryController, appConfig = appConfig)
    }

    @Provides
    @Singleton
    fun provideHomeRemoteDataSource(retrofit: Retrofit): HomeRemoteDataSource {
        val homeDataSource = retrofit.create(HomeDataSource::class.java)
        return HomeRemoteDataSource(homeDataSource)
    }

    @Provides
    @Singleton
    @CoreModule.FirebaseSource
    fun provideHomeFirebaseDataSource(firebaseFirestore: FirebaseFirestore): HomeFirebaseDataSource {
        return HomeFirebaseDataSource(firebaseFirestore)
    }
}
