package com.fabianospdev.mindflow.features.settings.domain.usecases

import com.fabianospdev.mindflow.core.helpers.exceptions.NetworkException
import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository

class SettingsRemoteUseCaseImpl(private val settingsRemoteRepository: SettingsRemoteRepository) : SettingsRemoteUseCase {

    override suspend fun getSettings(): Result<GlobalSettingsEntity> {
        return settingsRemoteRepository.getSettings()
    }

    override suspend fun setSettings(model: GlobalSettingsEntity, userId: String): Result<SettingsResponseEntity> {
        return try {
            settingsRemoteRepository.setSettings(model, userId)
        } catch (error: NetworkException) {
            Result.failure(error)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun setDarkMode(enabled: Boolean) {
        settingsRemoteRepository.getSettings()
    }
}