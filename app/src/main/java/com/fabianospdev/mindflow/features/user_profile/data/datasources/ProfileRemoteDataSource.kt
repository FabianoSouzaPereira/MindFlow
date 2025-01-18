package com.fabianospdev.mindflow.features.user_profile.data.datasources

import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileFirestoreModel
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val retrofitService: ProfileDataSource
) : ProfileDataSource {

    override suspend fun getProfileContent(): ProfileFirestoreModel {
        try {

            return retrofitService.getProfileContent()
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }

    override suspend fun setProfileContent(model: ProfileFirestoreModel): ProfileFirestoreModel {
        TODO("Not yet implemented")
    }
}