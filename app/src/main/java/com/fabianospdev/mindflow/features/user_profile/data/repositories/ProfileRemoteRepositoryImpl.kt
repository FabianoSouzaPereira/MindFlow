package com.fabianospdev.mindflow.features.user_profile.data.repositories

import com.fabianospdev.mindflow.features.user_profile.data.datasources.ProfileDataSource
import com.fabianospdev.mindflow.features.user_profile.domain.entities.ProfileEntity
import com.fabianospdev.mindflow.features.user_profile.domain.repositories.ProfileRemoteRepository
import javax.inject.Inject

class ProfileRemoteRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRemoteRepository {

    override suspend fun getProfileContent(): Result<ProfileEntity> {
        return try {
            val response = profileDataSource.getProfileContent()
            return Result.success(response as ProfileEntity)

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}