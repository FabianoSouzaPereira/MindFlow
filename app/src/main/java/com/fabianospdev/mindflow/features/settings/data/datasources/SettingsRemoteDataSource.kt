package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsRequestModel
import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import javax.inject.Inject

class SettingsRemoteDataSource @Inject constructor(

) : SettingsDataSource {
    override suspend fun getSettings(request: SettingsRequestModel): SettingsResponseModel {
        TODO("Not yet implemented")
    }
}