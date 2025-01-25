package com.fabianospdev.mindflow.features.settings.data.datasources

import com.fabianospdev.mindflow.features.settings.data.models.SettingsResponseModel
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SettingsFirebaseDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : SettingsDataSource {

    override suspend fun getSettings(): GlobalSettingsFirestoreModel {
        return try {
            val documentSnapshot = firestore.collection("GlobalSettings")
                .document("default").get().await()

            if (documentSnapshot.exists()) {
                documentSnapshot.toObject(GlobalSettingsFirestoreModel::class.java)
                    ?: throw IllegalStateException("Erro ao converter para GlobalSettingsFirestoreModel")
            } else {
                throw NoSuchElementException("Configuração padrão não encontrada")
            }
        } catch (e: Exception) {
            throw e
        }
    }


    override suspend fun setSettings(model: GlobalSettingsFirestoreModel): SettingsResponseModel {
        return try {

            val globalSettingsRef = firestore.collection("GlobalSettings").document("default")
            globalSettingsRef.set(model, SetOptions.merge()).await()

            SettingsResponseModel(success = true)

        } catch (e: Exception) {
            SettingsResponseModel(success = false, message = e.message)
        }
    }
}
