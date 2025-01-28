package com.fabianospdev.mindflow.features.profile.domain.usecases

import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import com.fabianospdev.mindflow.features.profile.domain.entities.ProfileEntity

interface ProfileRemoteUseCase {
    suspend fun getProfileContent(): Result<ProfileEntity>
    suspend fun setProfileContent(model: ProfileEntity, userId: String): Result<ProfileResponseEntity>
}