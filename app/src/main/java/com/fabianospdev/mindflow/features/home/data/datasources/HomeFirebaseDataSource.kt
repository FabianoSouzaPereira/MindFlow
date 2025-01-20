package com.fabianospdev.mindflow.features.home.data.datasources

import com.fabianospdev.mindflow.features.home.data.models.HomeResponseModel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class HomeFirebaseDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore?
) : HomeDataSource {
    override suspend fun getHomeContent(): HomeResponseModel {
        TODO("Not yet implemented")
    }
}