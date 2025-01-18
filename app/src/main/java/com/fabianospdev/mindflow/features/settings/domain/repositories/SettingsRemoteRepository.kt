package com.fabianospdev.mindflow.features.settings.domain.repositories

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity

interface SettingsRemoteRepository {
    suspend fun getSettings(): Result<GlobalSettingsEntity>
    suspend fun setSettings(model: GlobalSettingsEntity): Result<SettingsResponseEntity>
}