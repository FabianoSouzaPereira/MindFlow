package com.fabianospdev.mindflow.features.profile.data.datasources

import android.util.Log
import com.fabianospdev.mindflow.features.profile.data.model.ProfileFirestoreModel
import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileFirebaseDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProfileDataSource() {

    override suspend fun getProfileContent(): ProfileFirestoreModel {
        return try {
            val documentSnapshot = firestore.collection("users")
                .document("default").get().await()

            if (documentSnapshot.exists()) {
                documentSnapshot.toObject(ProfileFirestoreModel::class.java)
                    ?: throw IllegalStateException("Erro ao converter para GlobalSettingsFirestoreModel")
            } else {
                throw NoSuchElementException("Configuração padrão não encontrada")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun setProfileContent(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        return try {
            val profileRef = firestore.collection("users")
                .document(userId)
                .collection("profile")
                .document(userId)

            profileRef.set(model).await()
            ProfileResponseEntity(success = true)
        } catch (e: Exception) {
            ProfileResponseEntity(success = false, message = e.message)
        }
    }

    override suspend fun setPreferences(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        return try {
            val preferencesRef = firestore.collection("users")
                .document(userId)
                .collection("profile")
                .document(userId)

            preferencesRef.set(model.preferences).await()

            Log.d("Firestore", "Profile settings added successfully")
            ProfileResponseEntity(success = true)
        } catch (e: Exception) {
            Log.w("Firestore", "Error adding profile", e)
            ProfileResponseEntity(success = false, message = e.message)
        }
    }

    override suspend fun setSocialLinks(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        return try {
            val profileRef = firestore.collection("users")
                .document(userId)
                .collection("profile")
                .document(userId)

            // Atualiza apenas o campo "socialLinks"
            profileRef.set(model.socialLinks).await()
            ProfileResponseEntity(success = true)
        } catch (e: Exception) {
            ProfileResponseEntity(success = false, message = e.message)
        }
    }

    override suspend fun updatePreferences(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        return try {
            val preferencesRef = firestore.collection("users")
                .document(userId)
                .collection("profile")
                .document(userId)

            preferencesRef.update(model.preferences).await()
            ProfileResponseEntity(success = true)
        } catch (e: Exception) {
            ProfileResponseEntity(success = false, message = e.message)
        }
    }

    override suspend fun updateSocialLinks(model: ProfileFirestoreModel, userId: String): ProfileResponseEntity {
        return try {
            val profileRef = firestore.collection("users")
                .document(userId)
                .collection("profile")
                .document(userId)

            // Atualiza apenas o campo "socialLinks"
            profileRef.update("socialLinks", model.socialLinks).await()
            ProfileResponseEntity(success = true)
        } catch (e: Exception) {
            ProfileResponseEntity(success = false, message = e.message)
        }
    }
}