package com.fabianospdev.mindflow.features.user_profile.domain.repositories

import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileResponseEntity
import com.fabianospdev.mindflow.features.user_profile.domain.entities.ProfileEntity

interface ProfileRemoteRepository {
    suspend fun getProfileContent(): Result<ProfileEntity>
    suspend fun setProfileContent(model: ProfileEntity): ProfileResponseEntity
}