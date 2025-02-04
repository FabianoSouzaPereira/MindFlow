package com.fabianospdev.mindflow.core.di

import android.content.Context
import com.fabianospdev.mindflow.core.database.AppDatabase
import com.fabianospdev.mindflow.core.database.dao.UserDao
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.DefaultRetryController
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.login.data.datasources.local.LoginLocalDataSource
import com.fabianospdev.mindflow.features.login.data.datasources.local.LoginLocalDataSourceImpl
import com.fabianospdev.mindflow.features.login.data.repositories.LoginLocalRepositoryImpl
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginLocalRepository
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginLocalUsecase
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginLocalUsecaseImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FirebaseSource

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitSource

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetryController(): RetryController {
        return DefaultRetryController(maxRetries = 3)
    }

    @Provides
    @Singleton
    fun provideAppConfig(@ApplicationContext context: Context): AppConfig {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isUsingFirebase = sharedPreferences.getBoolean("is_using_firebase", true)
        return AppConfig(initialState = isUsingFirebase, context = context)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideLoginLocalDataSource(userDao: UserDao): LoginLocalDataSource {
        return LoginLocalDataSourceImpl(userDao)
    }

    @Provides
    fun provideLoginLocalRepository(repository: UserDao): LoginLocalRepository {
        return LoginLocalRepositoryImpl(repository)
    }

    @Provides
    fun provideUserUseCase(repository: LoginLocalRepository): LoginLocalUsecase {
        return LoginLocalUsecaseImpl(repository)
    }
}
