package com.fabianospdev.mindflow.features.settings.data.repositories

import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsApi
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsDataSource
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository
import javax.inject.Inject

class SettingsRemoteRepositoryImpl @Inject constructor(
    private val settingsApi: SettingsApi,
    private val settingsDataSource: SettingsDataSource
) : SettingsRemoteRepository {
    override suspend fun getSettings(): Result<GlobalSettingsEntity> {
        return try {
            val response = settingsDataSource.getSettings()
            return Result.success(response as GlobalSettingsEntity)

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun setSettings(model: GlobalSettingsEntity): Result<SettingsResponseEntity> {
        return try {
            val response = when (model) {
                is GlobalSettingsRelationalModel -> {
                    val response = settingsApi.setSettings(model)
                    Result.success(response as SettingsResponseEntity)
                }

                is GlobalSettingsFirestoreModel -> {
                    val response = settingsDataSource.setSettings(model)
                    Result.success(response as SettingsResponseEntity)
                }

                else -> throw IllegalArgumentException("Invalid model type")
            }
            Result.success(response as SettingsResponseEntity)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

}