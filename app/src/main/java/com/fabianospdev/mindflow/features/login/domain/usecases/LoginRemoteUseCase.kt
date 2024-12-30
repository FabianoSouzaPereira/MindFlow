package com.fabianospdev.mindflow.features.login.domain.usecases

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import retrofit2.Response

interface LoginRemoteUseCase {
    suspend fun login(email: String, password: String): Result<LoginResponseEntity>
}