package com.fabianospdev.mindflow.features.user_profile.data.datasources

import android.util.Log
import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileFirestoreModel
import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileResponseEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileFirebaseDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProfileDataSource() {

    override suspend fun getProfileContent(): ProfileFirestoreModel {
        throw Throwable("Authentication error")
    }

    override suspend fun setProfileContent(model: ProfileFirestoreModel): ProfileResponseEntity {
        return try {
            val profileRef = firestore.collection("userProfile").document("default")

            profileRef.set(model).await()

            Log.d("Firestore", "Profile settings added successfully")
            ProfileResponseEntity(success = true)
        } catch (e: Exception) {
            Log.w("Firestore", "Error adding profile", e)
            ProfileResponseEntity(success = false, message = e.message)
        }
    }

}