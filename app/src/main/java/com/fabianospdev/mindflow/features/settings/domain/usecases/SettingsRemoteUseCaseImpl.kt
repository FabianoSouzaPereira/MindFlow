package com.fabianospdev.mindflow.features.settings.domain.usecases

import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.firebase.GlobalSettingsFirestoreEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.relacional.GlobalSettingsRemoteEntity
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository

class SettingsRemoteUseCaseImpl(private val settingsRemoteRepository: SettingsRemoteRepository) : SettingsRemoteUseCase {
    override suspend fun getSettings(): Result<SettingsResponseEntity> {
        return settingsRemoteRepository.getSettings()
    }

    override suspend fun setSettings(model: GlobalSettingsRemoteEntity): Result<SettingsResponseModel> {
        TODO("Not yet implemented")
    }

    override suspend fun setSettings(model: GlobalSettingsFirestoreEntity): Result<SettingsResponseModel> {
        TODO("Not yet implemented")
    }
}