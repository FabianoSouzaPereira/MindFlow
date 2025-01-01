package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsRequestModel
import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel


interface SettingsDataSource {
    suspend fun getSettings(request: SettingsRequestModel): SettingsResponseModel
}