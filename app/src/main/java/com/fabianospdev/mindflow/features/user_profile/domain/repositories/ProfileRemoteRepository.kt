package com.fabianospdev.mindflow.features.user_profile.domain.repositories

import com.fabianospdev.mindflow.features.user_profile.domain.entities.ProfileEntity

interface ProfileRemoteRepository {
    suspend fun getProfileContent(): Result<ProfileEntity>
}