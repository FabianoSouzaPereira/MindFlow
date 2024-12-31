package com.fabianospdev.mindflow.features.login.data.datasources

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
/**
 *  Any data source that implements LoginDataSource needs to be able to log in
 *  by receiving a LoginRequestModel and returning a Result<LoginResponseEntity>.
 */
interface LoginDataSource {
    suspend fun login(request: LoginRequestModel): Result<LoginResponseEntity>
}
