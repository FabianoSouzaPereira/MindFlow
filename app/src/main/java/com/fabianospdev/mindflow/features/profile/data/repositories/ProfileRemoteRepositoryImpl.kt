package com.fabianospdev.mindflow.features.profile.data.repositories

import com.fabianospdev.mindflow.features.profile.data.datasources.ProfileFirebaseDataSource
import com.fabianospdev.mindflow.features.profile.data.datasources.ProfileRemoteDataSource
import com.fabianospdev.mindflow.features.profile.data.model.ProfileFirestoreModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileRelationalModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import com.fabianospdev.mindflow.features.profile.domain.entities.ProfileEntity
import com.fabianospdev.mindflow.features.profile.domain.repositories.ProfileRemoteRepository
import javax.inject.Inject

class ProfileRemoteRepositoryImpl @Inject constructor(
    private val profileFirebaseDataSource: ProfileFirebaseDataSource,
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRemoteRepository {

    override suspend fun getProfileContent(): Result<ProfileEntity> {
        return try {
            val response = profileFirebaseDataSource.getProfileContent()
            return Result.success(response as ProfileEntity)

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun setProfileContent(model: ProfileEntity, userId: String): Result<ProfileResponseEntity> {
        return try {
            val response = when (model) {
                is ProfileFirestoreModel -> {
                    val response = profileFirebaseDataSource.setProfileContent(model, userId)
                    Result.success(response as ProfileResponseEntity)
                }

                is ProfileRelationalModel -> {
                    val response = profileRemoteDataSource.setProfileContent(model, userId)
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