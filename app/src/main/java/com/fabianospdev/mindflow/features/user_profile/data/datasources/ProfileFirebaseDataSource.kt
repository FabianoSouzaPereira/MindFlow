package com.fabianospdev.mindflow.features.user_profile.data.datasources

import android.util.Log
import com.fabianospdev.mindflow.features.user_profile.data.model.ProfileFirestoreModel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ProfileFirebaseDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProfileDataSource {

    override suspend fun getProfileContent(): ProfileFirestoreModel {
        throw Throwable("Authentication error")
    }

    override suspend fun setProfileContent(model: ProfileFirestoreModel): ProfileFirestoreModel {
        return try {
            val globalSettingsRef = firestore.collection("GlobalSettingsConfiguration").document("default")

            globalSettingsRef.set(testGlobalSettings)
                .addOnSuccessListener {
                    Log.d("Firestore", "Global settings added successfully")
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error adding global settings", e)
                }
        } catch (e: Exception) {
            Log.w("Firestore", "Error adding global settings", e)
        }
    }
}