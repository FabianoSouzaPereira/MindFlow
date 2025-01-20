package com.fabianospdev.mindflow.features.settings.data.repositories

import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsApi
import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsDataSource
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository
import javax.inject.Inject

class SettingsRemoteRepositoryImpl @Inject constructor(
    private val appConfig: AppConfig,
    private val settingsApi: SettingsApi,
    private val settingsDataSource: SettingsDataSource
) : SettingsRemoteRepository {

    override suspend fun getSettings(): Result<GlobalSettingsEntity> {
        return try {
            val response: GlobalSettingsEntity = if (appConfig.isUsingFirebase.value) {

                settingsDataSource.getSettings()
            } else {

                settingsApi.getSettings()
            }
            Result.success(response)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun setSettings(model: GlobalSettingsEntity): Result<SettingsResponseEntity> {
        return try {
            val response = when (model) {
                is GlobalSettingsRelationalModel -> {
                    settingsApi.setSettings(model)
                }

                is GlobalSettingsFirestoreModel -> {
                    settingsDataSource.setSettings(model)
                }

                else -> throw IllegalArgumentException("Invalid model type")
            }
            Result.success(response)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}