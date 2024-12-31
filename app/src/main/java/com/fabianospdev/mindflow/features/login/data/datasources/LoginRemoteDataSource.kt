package com.fabianospdev.mindflow.features.login.data.datasources

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val retrofitService: LoginDataSource
) : LoginDataSource {

    override suspend fun login(request: LoginRequestModel): Result<LoginResponseModel> {
        return try {
            val result = retrofitService.login(request)
            if (result.isSuccess) {
                result
            } else {
                Result.failure(Throwable("Login failed: ${result}"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Authentication error: ${e.message}", e))
        }
    }
}
