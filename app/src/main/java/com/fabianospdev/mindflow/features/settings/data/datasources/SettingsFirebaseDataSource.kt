package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsRequestModel
import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SettingsFirebaseDataSource  @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SettingsDataSource {
    override suspend fun getSettings(request: SettingsRequestModel): SettingsResponseModel {
        TODO("Not yet implemented")
    }
}