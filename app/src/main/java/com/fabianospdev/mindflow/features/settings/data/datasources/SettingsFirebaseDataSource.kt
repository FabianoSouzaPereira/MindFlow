package com.fabianospdev.mindflow.features.settings.data.datasources

import android.util.Log
import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SettingsFirebaseDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : SettingsDataSource() {

    override suspend fun getSettings(): GlobalSettingsRelationalModel? {
        return try {
            val documentSnapshot = firestore.collection("GlobalSettingsConfiguration")
                .document("default").get().await()

            if (documentSnapshot.exists()) {
                documentSnapshot.toObject(GlobalSettingsRelationalModel::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun setSettings(model: GlobalSettingsFirestoreModel): SettingsResponseModel {
        return try {
            val globalSettingsRef = firestore.collection("GlobalSettingsConfiguration").document("default")
            globalSettingsRef.set(model, SetOptions.merge()).await()

            Log.d("Firestore", "Global settings added successfully")

            SettingsResponseModel(success = true)

        } catch (e: Exception) {
            Log.w("Firestore", "Error adding global settings", e)
            SettingsResponseModel(success = false, message = e.message)
        }
    }
}
