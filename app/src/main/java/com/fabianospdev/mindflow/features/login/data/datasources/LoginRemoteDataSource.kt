package com.fabianospdev.mindflow.features.login.data.datasources

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val retrofitService: LoginDataSource
) : LoginDataSource {

    override suspend fun login(request: LoginRequestModel): LoginResponseModel {
        try {
            val response = retrofitService.login(request)

            if (response.token.isNotEmpty()) {
                return response
            } else {
                throw Throwable("Login failed: Token is empty")
            }
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }
}
