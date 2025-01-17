package com.fabianospdev.mindflow.features.emotional_record.di

import android.content.Context
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.emotional_record.data.datasources.EmotionalRecordDatasource
import com.fabianospdev.mindflow.features.emotional_record.data.datasources.EmotionalRecordFireBaseDatasource
import com.fabianospdev.mindflow.features.emotional_record.data.datasources.EmotionalRecordRemoteDatasource
import com.fabianospdev.mindflow.features.emotional_record.data.repositories.EmotionalRecordRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.emotional_record.domain.repositories.EmotionalRecordRemoteRepository
import com.fabianospdev.mindflow.features.emotional_record.domain.usecases.EmotionalRecordRemoteUseCase
import com.fabianospdev.mindflow.features.emotional_record.domain.usecases.EmotionalRecordRemoteUseCaseImpl
import com.fabianospdev.mindflow.features.emotional_record.presentation.viewmodel.EmotionalRecordViewModel
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
object EmotionalRecordModel {

    @Provides
    @Singleton
    fun provideEmotionalRecordDataSource(
        appConfig: AppConfig,
        retrofitDataSource: EmotionalRecordRemoteDatasource,
        firebaseDataSource: EmotionalRecordFireBaseDatasource
    ): EmotionalRecordDatasource {
        return if (appConfig.isUsingFirebase.value) firebaseDataSource else retrofitDataSource
    }

    @Provides
    @Singleton
    fun provideEmotionalRecordRemoteDataSource(retrofit: Retrofit): EmotionalRecordRemoteDatasource {
        val emotionalRecordDataSource = retrofit.create(EmotionalRecordDatasource::class.java)
        return EmotionalRecordRemoteDatasource(emotionalRecordDataSource)
    }

    @Provides
    @Singleton
    fun provideEmotionalRecordFirebaseDataSource(firebaseAuth: FirebaseAuth): EmotionalRecordFireBaseDatasource {
        return EmotionalRecordFireBaseDatasource(firebaseAuth)
    }

    @Provides
    fun provideEmotionalRecordRemoteRepository(
        remoteDataSource: EmotionalRecordDatasource,
        @ApplicationContext context: Context
    ): EmotionalRecordRemoteRepository {
        return EmotionalRecordRemoteRepositoryImpl(emotionalRecordDatasource = remoteDataSource, context = context)
    }

    @Provides
    fun provideEmotionalRecordRemoteUseCase(
        repository: EmotionalRecordRemoteRepository
    ): EmotionalRecordRemoteUseCase {
        return EmotionalRecordRemoteUseCaseImpl(repository = repository)
    }

    @Provides
    fun providerEmotionalRecordViewModel(
        emotionalRecordRemoteUseCase: EmotionalRecordRemoteUseCase,
        retryController: RetryController,
        appConfig: AppConfig
    ): EmotionalRecordViewModel {
        return EmotionalRecordViewModel(
            useCase = emotionalRecordRemoteUseCase,
            retryController = retryController,
            appConfig = appConfig
        )
    }
}