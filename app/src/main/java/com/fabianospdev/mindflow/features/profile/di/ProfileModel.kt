package com.fabianospdev.mindflow.features.profile.di

import com.fabianospdev.mindflow.core.di.CoreModule
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.profile.data.datasources.ProfileApi
import com.fabianospdev.mindflow.features.profile.data.datasources.ProfileFirebaseDataSource
import com.fabianospdev.mindflow.features.profile.data.datasources.ProfileRemoteDataSource
import com.fabianospdev.mindflow.features.profile.data.repositories.ProfileRemoteRepositoryImpl
import com.fabianospdev.mindflow.features.profile.domain.repositories.ProfileRemoteRepository
import com.fabianospdev.mindflow.features.profile.domain.usecases.ProfileRemoteUseCase
import com.fabianospdev.mindflow.features.profile.domain.usecases.ProfileRemoteUseCaseImpl
import com.fabianospdev.mindflow.features.profile.presentation.viewmodel.ProfileViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModel {

    @Provides
    @Singleton
    fun provideProfileDataSource(
        retrofitDataSource: ProfileApi
    ): ProfileApi {
        return retrofitDataSource
    }

    @Provides
    fun provideProfileRepository(
        profileFirebaseDataSource: ProfileFirebaseDataSource,
        profileRemoteDataSource: ProfileRemoteDataSource
    ): ProfileRemoteRepository {
        return ProfileRemoteRepositoryImpl(profileFirebaseDataSource, profileRemoteDataSource)
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
        val profileApi = retrofit.create(ProfileApi::class.java)
        return ProfileRemoteDataSource(profileApi)
    }

    @Provides
    @Singleton
    @CoreModule.FirebaseSource
    fun provideProfileFirebaseDataSource(firebaseFirestore: FirebaseFirestore): ProfileFirebaseDataSource {
        return ProfileFirebaseDataSource(firebaseFirestore)
    }
}