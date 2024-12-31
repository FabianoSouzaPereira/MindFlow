package com.fabianospdev.mindflow.features.settings.domain.usecases

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository

class SettingsRemoteUseCaseImpl(private val settingsRemoteRepository: SettingsRemoteRepository) : SettingsRemoteUseCase{
    override suspend fun getSettings(): Result<SettingsResponseEntity> {
        TODO("Not yet implemented")
    }
}