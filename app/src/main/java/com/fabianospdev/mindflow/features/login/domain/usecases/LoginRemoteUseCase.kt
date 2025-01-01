package com.fabianospdev.mindflow.features.login.domain.usecases

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity

interface LoginRemoteUseCase {
    suspend fun login(email: String, password: String): LoginResponseEntity
}