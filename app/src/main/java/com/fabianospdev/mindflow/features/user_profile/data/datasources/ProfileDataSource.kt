package com.fabianospdev.mindflow.features.user_profile.data.datasources

import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileModel

interface ProfileDataSource {
    suspend fun getProfileContent(): ProfileModel
}