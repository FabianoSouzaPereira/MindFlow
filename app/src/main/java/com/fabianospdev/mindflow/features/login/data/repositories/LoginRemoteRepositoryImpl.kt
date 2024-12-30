package com.fabianospdev.mindflow.features.login.data.repositories

import com.fabianospdev.mindflow.features.login.data.datasources.LoginRemoteDataSource
import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginRemoteRepository
import javax.inject.Inject

class LoginRemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRemoteRepository {

    override suspend fun login(email: String, password: String): Result<LoginResponseEntity> {
        return try {
            val requestModel = LoginRequestModel(email, password)

            val response = remoteDataSource.login(requestModel)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable("Login failed: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Authentication error: ${e.message}", e))
        }
    }
}