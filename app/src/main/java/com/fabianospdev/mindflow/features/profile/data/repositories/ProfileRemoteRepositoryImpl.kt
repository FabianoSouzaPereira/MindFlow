package com.fabianospdev.mindflow.features.profile.data.repositories

import com.fabianospdev.mindflow.features.profile.data.datasources.ProfileDataSource
import com.fabianospdev.mindflow.features.profile.data.model.ProfileFirestoreModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileRelationalModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import com.fabianospdev.mindflow.features.profile.domain.entities.ProfileEntity
import com.fabianospdev.mindflow.features.profile.domain.repositories.ProfileRemoteRepository
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

    override suspend fun setProfileContent(model: ProfileEntity): Result<ProfileResponseEntity> {
        return try {
            val response = when (model) {
                is ProfileFirestoreModel -> {
                    val response = profileDataSource.setProfileContent(model)
                    Result.success(response as ProfileResponseEntity)
                }

                is ProfileRelationalModel -> {
                    val response = profileDataSource.setProfileContent(model)
                    Result.success(response as ProfileResponseEntity)
                }

                else -> {
                    throw IllegalArgumentException("Invalid model type")
                }
            }
            Result.success(response as ProfileResponseEntity)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}