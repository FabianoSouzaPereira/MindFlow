package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel


interface SettingsDataSource {
    suspend fun getSettings(): GlobalSettingsFirestoreModel
    suspend fun setSettings(model: GlobalSettingsFirestoreModel): SettingsResponseModel
}