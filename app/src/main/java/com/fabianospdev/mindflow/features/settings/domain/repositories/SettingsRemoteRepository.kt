package com.fabianospdev.mindflow.features.settings.domain.repositories

import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.firebase.GlobalSettingsFirestoreEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.relacional.GlobalSettingsRemoteEntity

interface SettingsRemoteRepository {
    suspend fun getSettings(): Result<SettingsResponseEntity>
    suspend fun setSettings(model: GlobalSettingsRemoteEntity): Result<SettingsResponseModel>
    suspend fun setSettings(model: GlobalSettingsFirestoreEntity): Result<SettingsResponseModel>
}