package com.fabianospdev.mindflow.features.login.domain.usecases

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginRemoteRepository
import javax.inject.Inject

class LoginRemoteUseCaseImpl @Inject constructor(
    private val repository: LoginRemoteRepository
) : LoginRemoteUseCase {
    override suspend fun login(email: String, password: String): LoginResponseEntity {
        return repository.login(email, password)
    }
}
