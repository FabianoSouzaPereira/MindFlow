package com.fabianospdev.mindflow.features.user_profile.domain.usecases

import com.fabianospdev.mindflow.features.user_profile.domain.entities.ProfileEntity

interface ProfileRemoteUseCase {
    suspend fun getProfileContent(): Result<ProfileEntity>
}