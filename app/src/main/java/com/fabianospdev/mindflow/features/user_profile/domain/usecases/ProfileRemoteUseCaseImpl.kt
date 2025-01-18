package com.fabianospdev.mindflow.features.user_profile.domain.usecases

import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileResponseEntity
import com.fabianospdev.mindflow.features.user_profile.domain.entities.ProfileEntity
import com.fabianospdev.mindflow.features.user_profile.domain.repositories.ProfileRemoteRepository
import javax.inject.Inject

class ProfileRemoteUseCaseImpl @Inject constructor(
    private val profileRemoteRepository: ProfileRemoteRepository
) : ProfileRemoteUseCase {

    override suspend fun getProfileContent(): Result<ProfileEntity> {
        return profileRemoteRepository.getProfileContent()
    }

    override suspend fun setProfileContent(model: ProfileEntity): ProfileResponseEntity {
        return profileRemoteRepository.setProfileContent(model = model)
    }
}