package com.fabianospdev.mindflow.features.user_profile.di

import android.content.Context
import com.fabianospdev.mindflow.core.di.CoreModule
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.user_profile.data.datasources.ProfileDataSource
import com.fabianospdev.mindflow.features.user_profile.data.datasources.ProfileFirebaseDataSource
import com.fabianospdev.mindflow.features.user_profile.data.datasources.ProfileRemoteDataSource
import com.fabianospdev.mindflow.features.user_profile.data.repositories.ProfileRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.user_profile.domain.repositories.ProfileRemoteRepository
import com.fabianospdev.mindflow.features.user_profile.domain.usecases.ProfileRemoteUseCase
import com.fabianospdev.mindflow.features.user_profile.domain.usecases.ProfileRemoteUseCaseImpl
import com.fabianospdev.mindflow.features.user_profile.presentation.viewmodel.ProfileViewModel
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
object ProfileModel {

    @Provides
    @Singleton
    fun provideProfileDataSource(
        appConfig: AppConfig,
        retrofitDataSource: ProfileRemoteDataSource,
        firebaseDataSource: ProfileFirebaseDataSource
    ): ProfileDataSource {
        return if (appConfig.isUsingFirebase.value) firebaseDataSource else retrofitDataSource
    }

    @Provides
    fun provideProfileRepository(
        remoteDataSource: ProfileDataSource,
        @ApplicationContext context: Context
    ): ProfileRemoteRepository {
        return ProfileRemoteRepositoryImpl(remoteDataSource, context)
    }

    @Provides
    fun provideProfileUseCase(
        repository: ProfileRemoteRepository
    ): ProfileRemoteUseCase {
        return ProfileRemoteUseCaseImpl(repository)
    }

    @Provides
    fun providerProfileViewModel(
        profileRemoteUseCase: ProfileRemoteUseCase,
        retryController: RetryController,
        appConfig: AppConfig
    ): ProfileViewModel {
        return ProfileViewModel(useCase = profileRemoteUseCase, retryController = retryController, appConfig = appConfig)
    }

    @Provides
    @Singleton
    fun provideProfileRemoteDataSource(retrofit: Retrofit): ProfileRemoteDataSource {
        val profileDataSource = retrofit.create(ProfileDataSource::class.java)
        return ProfileRemoteDataSource(profileDataSource)
    }

    @Provides
    @Singleton
    @CoreModule.FirebaseSource
    fun provideProfileFirebaseDataSource(firebaseAuth: FirebaseAuth): ProfileFirebaseDataSource {
        return ProfileFirebaseDataSource(firebaseAuth)
    }
}