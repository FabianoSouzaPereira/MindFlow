package com.fabianospdev.mindflow.features.user_profile.data.datasources

import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileModel
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val retrofitService: ProfileDataSource
) : ProfileDataSource {

    override suspend fun getProfileContent(): ProfileModel {
        try {

            return retrofitService.getProfileContent()
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }
}