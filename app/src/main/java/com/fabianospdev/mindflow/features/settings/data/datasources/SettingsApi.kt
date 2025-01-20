package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SettingsApi {
    @GET("settings")
    suspend fun getSettings(): GlobalSettingsRelationalModel

    @POST("settings")
    suspend fun setSettings(@Body model: GlobalSettingsRelationalModel): SettingsResponseModel
}
