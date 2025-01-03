package com.fabianospdev.mindflow.features.login.data.datasources

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *  Any data source that implements LoginDataSource needs to be able to log in
 *  by receiving a LoginRequestModel and returning a Result<LoginResponseEntity>.
 */
interface LoginDataSource {
    @POST("login")
    suspend fun login(@Body request: LoginRequestModel): Response<LoginResponseModel>
}
