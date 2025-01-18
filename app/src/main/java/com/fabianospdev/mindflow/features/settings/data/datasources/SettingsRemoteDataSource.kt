package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import javax.inject.Inject

class SettingsRemoteDataSource @Inject constructor(
    private val settings: SettingsDataSource
) : SettingsDataSource() {
    override suspend fun getSettings(): GlobalSettingsRelationalModel? {
        try {

            return settings.getSettings()
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }

    override suspend fun setSettings(model: GlobalSettingsRelationalModel): SettingsResponseModel {
        try {

            return settings.setSettings(model = model)
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }

}