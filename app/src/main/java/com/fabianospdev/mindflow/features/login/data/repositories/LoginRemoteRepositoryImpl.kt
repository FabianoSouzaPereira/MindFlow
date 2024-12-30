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
            val result = remoteDataSource.login(requestModel)

            if (result.isSuccess) {
                val loginResponse = result.getOrNull()
             // Directly returns the LoginResponseModel as LoginRemoteEntity since it implements LoginRemoteEntity
                if (loginResponse != null) {
                    Result.success(loginResponse)
                } else {
                    Result.failure(Throwable("Failed to map to LoginRemoteEntity"))
                }
            } else {
                Result.failure(Throwable("Login failed: ${result.isFailure}"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Authentication error: ${e.message}", e))
        }
    }

}