package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel
import com.fabianospdev.mindflow.features.settings.data.models.globalSettings.GlobalSettingsRelationalModel


abstract class SettingsDataSource {
    abstract suspend fun getSettings(): SettingsResponseModel

    open suspend fun setSettings(model: GlobalSettingsRelationalModel): SettingsResponseModel {
        throw NotImplementedError("setSettings(GlobalSettingsRemoteEntity) not implemented")
    }

    open suspend fun setSettings(model: GlobalSettingsFirestoreModel): SettingsResponseModel {
        throw NotImplementedError("setSettings(GlobalSettingsFirestoreEntity) not implemented")
    }
}