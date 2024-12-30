package com.fabianospdev.mindflow.features.login.domain.usecases

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginRemoteRepository
import retrofit2.Response

class LoginRemoteUseCaseImpl(private val repository: LoginRemoteRepository): LoginRemoteUseCase {
    override suspend fun login(email: String, password: String): Result<LoginResponseEntity> {
        return try {
            repository.login(password, password)
        } catch (e: Exception) {
            Result.failure(Exception("Erro ao tentar fazer login: ${e.message}", e))
        }
    }
}