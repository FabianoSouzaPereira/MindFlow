package com.fabianospdev.mindflow.features.home.data.datasources

import com.fabianospdev.mindflow.features.home.data.models.HomeResponseModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class HomeFirebaseDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth?
) : HomeDataSource {
    override suspend fun getHomeContent(): HomeResponseModel {
        TODO("Not yet implemented")
    }
}