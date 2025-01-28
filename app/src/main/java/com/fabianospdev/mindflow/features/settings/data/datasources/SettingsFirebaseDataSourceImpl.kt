package com.fabianospdev.mindflow.features.settings.data.datasources

import android.util.Log
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

            val userId = "9xMDsZTz65N80m0UPWHkMdDRJJ32"

            val documentSnapshot = firestore.collection("users")
                .document(userId)
                .collection("settings")
                .document(userId)
                .get()
                .await()


            if (documentSnapshot.exists()) {
                Log.d("Firestore", "Configuração encontrada para o usuário: $userId")

                documentSnapshot.toObject(GlobalSettingsFirestoreModel::class.java)
                    ?: throw IllegalStateException("Erro ao converter para GlobalSettingsFirestoreModel")
            } else {
                throw NoSuchElementException("Configuração não encontrada para o usuário: $userId")
            }
        } catch (e: Exception) {
            Log.e("Firestore", "Erro ao buscar configurações: ${e.message}", e)
            throw e
        }
    }


    override suspend fun setSettings(model: GlobalSettingsFirestoreModel, userId: String): SettingsResponseModel {
        return try {
            val userId1 = "9xMDsZTz65N80m0UPWHkMdDRJJ32"
            val profileRef = firestore.collection("users")
                .document(userId1)
                .collection("settings")
                .document(userId1)

            profileRef.set(model, SetOptions.merge()).await()
            SettingsResponseModel(success = true)

        } catch (e: Exception) {
            SettingsResponseModel(success = false, message = e.message)
        }
    }

    override suspend fun setSettingsServerAddress(model: GlobalSettingsFirestoreModel, userId: String): SettingsResponseModel {
        return try {
            val userId1 = "9xMDsZTz65N80m0UPWHkMdDRJJ32"

            val profileRef = firestore.collection("users")
                .document(userId1)
                .collection("serverAddress")
                .document(userId1)

            profileRef.set(model.serverAddress).await()
            SettingsResponseModel(success = true)

        } catch (e: Exception) {
            SettingsResponseModel(success = false, message = e.message)
        }
    }
}
