package com.fabianospdev.mindflow.features.profile.data.datasources

import com.fabianospdev.mindflow.features.profile.data.model.ProfileFirestoreModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileRelationalModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val retrofitService: ProfileApi
) : ProfileApi {

    override suspend fun getProfileContent(): ProfileFirestoreModel {
        try {

            return retrofitService.getProfileContent()
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }

    override suspend fun setProfileContent(model: ProfileRelationalModel, userId: String): ProfileResponseEntity {
        try {

            return retrofitService.setProfileContent(model = model, userId)
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }
}