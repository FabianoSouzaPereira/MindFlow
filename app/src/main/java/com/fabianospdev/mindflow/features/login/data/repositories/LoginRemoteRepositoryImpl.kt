package com.fabianospdev.mindflow.features.login.data.repositories

import com.fabianospdev.mindflow.features.login.data.datasources.LoginDataSource
import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginRemoteRepository
import javax.inject.Inject

class LoginRemoteRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRemoteRepository {
    override suspend fun login(email: String, password: String): LoginResponseEntity {
        return loginDataSource.login(LoginRequestModel(email, password))
    }
}
