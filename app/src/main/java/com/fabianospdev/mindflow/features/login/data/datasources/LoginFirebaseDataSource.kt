package com.fabianospdev.mindflow.features.login.data.datasources

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import retrofit2.Response
import javax.inject.Inject

class LoginFirebaseDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : LoginDataSource {

    override suspend fun login(request: LoginRequestModel): Response<LoginResponseModel> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(request.email, request.password).await()
            val loginResponse = LoginResponseModel(result.user?.uid ?: "")
            Response.success(loginResponse)
        } catch (e: Exception) {
            Response.error(
                400,
                okhttp3.ResponseBody.create(
                    okhttp3.MediaType.parse("text/plain"),
                    "Authentication error: ${e.message}"
                )
            )
        }
    }
}
