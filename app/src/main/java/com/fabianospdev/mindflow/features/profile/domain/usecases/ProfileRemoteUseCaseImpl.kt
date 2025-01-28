package com.fabianospdev.mindflow.features.profile.domain.usecases

import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import com.fabianospdev.mindflow.features.profile.domain.entities.ProfileEntity
import com.fabianospdev.mindflow.features.profile.domain.repositories.ProfileRemoteRepository
import javax.inject.Inject

class ProfileRemoteUseCaseImpl @Inject constructor(
    private val profileRemoteRepository: ProfileRemoteRepository
) : ProfileRemoteUseCase {

    override suspend fun getProfileContent(): Result<ProfileEntity> {
        return profileRemoteRepository.getProfileContent()
    }

    override suspend fun setProfileContent(model: ProfileEntity, userId: String): Result<ProfileResponseEntity> {
        return profileRemoteRepository.setProfileContent(model = model, userId)
    }
}