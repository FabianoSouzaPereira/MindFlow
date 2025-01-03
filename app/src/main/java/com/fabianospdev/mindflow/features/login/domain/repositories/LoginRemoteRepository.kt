package com.fabianospdev.mindflow.features.login.domain.repositories

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity

interface LoginRemoteRepository {
    suspend fun login(email: String, password: String): Result<LoginResponseEntity>
}