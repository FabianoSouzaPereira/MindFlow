package com.fabianospdev.mindflow.features.settings.domain.repositories

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity

interface SettingsRemoteRepository {
    suspend fun getSettings(): Result<SettingsResponseEntity>
}