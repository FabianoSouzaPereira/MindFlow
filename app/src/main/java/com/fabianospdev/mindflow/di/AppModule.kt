package com.fabianospdev.mindflow.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.fabianospdev.mindflow.MainViewModel
import com.fabianospdev.mindflow.core.database.AppDatabase
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.dataStore
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginLocalUsecase
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(app: Application): DataStore<Preferences> {
        return app.dataStore
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "app_database")
            .build()
    }

    @Provides
    fun providerMainViewModel(
        settingsUseCase: SettingsRemoteUseCase,
        loginLocalUsecase: LoginLocalUsecase,
        retryController: RetryController
    ): MainViewModel {
        return MainViewModel(settingsUseCase, loginLocalUsecase, retryController)
    }
}