package com.fabianospdev.mindflow.features.settings.domain.usecases

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity

interface SettingsRemoteUseCase {
    suspend fun getSettings(): Result<SettingsResponseEntity>
}