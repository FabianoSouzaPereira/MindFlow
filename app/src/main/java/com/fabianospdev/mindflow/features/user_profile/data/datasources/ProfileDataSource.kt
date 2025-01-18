package com.fabianospdev.mindflow.features.user_profile.data.datasources

import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileFirestoreModel

interface ProfileDataSource {
    suspend fun getProfileContent(): ProfileFirestoreModel
    suspend fun setProfileContent(model: ProfileFirestoreModel): ProfileFirestoreModel
}