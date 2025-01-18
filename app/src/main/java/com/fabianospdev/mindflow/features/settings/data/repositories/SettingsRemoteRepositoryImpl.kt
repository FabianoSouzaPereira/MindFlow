package com.fabianospdev.mindflow.features.settings.data.repositories

import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsDataSource
import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.firebase.GlobalSettingsFirestoreEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.relacional.GlobalSettingsRemoteEntity
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository
import javax.inject.Inject

class SettingsRemoteRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource
) : SettingsRemoteRepository {
    override suspend fun getSettings(): Result<SettingsResponseEntity> {
        return try {
            val response = settingsDataSource.getSettings()
            return Result.success(response as SettingsResponseEntity)

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun setSettings(model: GlobalSettingsRemoteEntity): Result<SettingsResponseModel> {
        TODO("Not yet implemented")
    }

    override suspend fun setSettings(model: GlobalSettingsFirestoreEntity): Result<SettingsResponseModel> {
        TODO("Not yet implemented")
    }
}