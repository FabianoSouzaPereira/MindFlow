package com.fabianospdev.mindflow.features.login.data.datasources

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRemoteDataSource {
    @POST("login")
    suspend fun login(@Body request: LoginRequestModel): Result<LoginResponseModel>
}
