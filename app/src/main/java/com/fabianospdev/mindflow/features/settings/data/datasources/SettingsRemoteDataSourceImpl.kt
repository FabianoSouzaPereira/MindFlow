package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import javax.inject.Inject

class SettingsRemoteDataSourceImpl @Inject constructor(
    private val settingsApi: SettingsApi
) : SettingsApi {

    override suspend fun getSettings(): GlobalSettingsRelationalModel {
        return settingsApi.getSettings()
    }

    override suspend fun setSettings(model: GlobalSettingsRelationalModel): SettingsResponseModel {
        return settingsApi.setSettings(model)
    }
}