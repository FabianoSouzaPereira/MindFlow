package com.fabianospdev.mindflow.features.settings.domain.usecases

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository

class SettingsRemoteUseCaseImpl(private val settingsRemoteRepository: SettingsRemoteRepository) : SettingsRemoteUseCase {
    override suspend fun getSettings(): Result<GlobalSettingsEntity> {
        return settingsRemoteRepository.getSettings()
    }

    override suspend fun setSettings(model: GlobalSettingsEntity): Result<SettingsResponseEntity> {
        return settingsRemoteRepository.setSettings(model)
    }
}