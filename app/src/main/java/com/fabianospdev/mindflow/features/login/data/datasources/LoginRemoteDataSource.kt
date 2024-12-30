package com.fabianospdev.mindflow.features.login.data.datasources

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRemoteDataSource {
    @POST("login")
    suspend fun login(@Body request: LoginRequestModel): Response<LoginResponseModel>
}
