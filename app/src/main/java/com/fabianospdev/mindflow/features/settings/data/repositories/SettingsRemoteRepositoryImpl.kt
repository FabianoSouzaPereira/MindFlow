package com.fabianospdev.mindflow.features.settings.data.repositories

import com.fabianospdev.mindflow.features.settings.data.datasources.SettingsDataSource
import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity
import com.fabianospdev.mindflow.features.settings.domain.repositories.SettingsRemoteRepository
import javax.inject.Inject

class SettingsRemoteRepositoryImpl @Inject constructor(
    private val  settingsDataSource: SettingsDataSource
) : SettingsRemoteRepository{
    override suspend fun getSettings(): Result<SettingsResponseEntity> {
        TODO("Not yet implemented")
    }
}