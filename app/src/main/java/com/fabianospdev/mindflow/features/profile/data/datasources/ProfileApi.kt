package com.fabianospdev.mindflow.features.profile.data.datasources

import com.fabianospdev.mindflow.features.profile.data.model.ProfileFirestoreModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileRelationalModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import retrofit2.http.Body
import retrofit2.http.GET

interface ProfileApi {
    @GET("profile")
    suspend fun getProfileContent(): ProfileFirestoreModel
    suspend fun setProfileContent(@Body model: ProfileRelationalModel, @Body userId: String): ProfileResponseEntity
}