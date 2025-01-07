package com.fabianospdev.mindflow.features.login.data.repositories

import android.content.Context
import com.fabianospdev.mindflow.core.helpers.TokenManager.saveToken
import com.fabianospdev.mindflow.features.login.data.datasources.LoginDataSource
import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginRemoteRepository
import javax.inject.Inject

class LoginRemoteRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val context: Context
) : LoginRemoteRepository {

    override suspend fun login(email: String, password: String): Result<LoginResponseEntity> {
        return try {
            val response = loginDataSource.login(LoginRequestModel(email, password))

            if (response.isSuccessful && response.body() != null) {
                saveToken(context, response.body()!!.token)
                Result.success(LoginResponseModel(response.body()!!.token))
            } else {

                Result.failure(Throwable("Login failed: server response not valid"))
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

}
