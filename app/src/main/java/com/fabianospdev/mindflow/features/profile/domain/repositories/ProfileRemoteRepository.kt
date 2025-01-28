package com.fabianospdev.mindflow.features.profile.domain.repositories

import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import com.fabianospdev.mindflow.features.profile.domain.entities.ProfileEntity

interface ProfileRemoteRepository {
    suspend fun getProfileContent(): Result<ProfileEntity>
    suspend fun setProfileContent(model: ProfileEntity, userId: String): Result<ProfileResponseEntity>
}