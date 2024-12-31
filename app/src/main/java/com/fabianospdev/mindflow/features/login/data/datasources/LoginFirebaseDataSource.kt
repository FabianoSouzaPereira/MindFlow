package com.fabianospdev.mindflow.features.login.data.datasources

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginFirebaseDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : LoginDataSource {

    override suspend fun login(request: LoginRequestModel): Result<LoginResponseEntity> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(request.email, request.password).await()
            val loginResponse = LoginResponseModel(result.user?.uid ?: "")
            Result.success(loginResponse)
        } catch (e: Exception) {
            Result.failure(Throwable("Authentication error: ${e.message}", e))
        }
    }
}
