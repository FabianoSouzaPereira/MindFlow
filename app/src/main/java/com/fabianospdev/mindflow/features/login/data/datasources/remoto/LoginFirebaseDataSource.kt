package com.fabianospdev.mindflow.features.login.data.datasources.remoto

import android.util.Log
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import retrofit2.Response
import javax.inject.Inject

class LoginFirebaseDataSource @Inject constructor(
    private val appConfig: AppConfig,
    private val firebaseAuth: FirebaseAuth
) : LoginDataSource {

    override suspend fun login(request: LoginRequestModel): Response<LoginResponseModel> {
        return try {
            val isAdminClaim: Boolean = false
            val result = firebaseAuth.signInWithEmailAndPassword(request.email, request.password).await()

            val idToken = result.user?.getIdToken(true)?.await()

            idToken?.let {
                val adminClaim = it.claims["admin"] as? Boolean
                if (adminClaim == true) {
                    Log.d("Auth", "User isn't admin")
                } else {
                    Log.d("Auth", "User is admin")
                }

                adminClaim?.let { it1 -> appConfig.saveAdminClaim(it1) }
            }

            val loginResponse = LoginResponseModel(
                token = idToken.toString(),
                isAdminClaim,
                result.user?.photoUrl,
                result.user?.uid,
                result.user?.isEmailVerified,
                result.user?.displayName
            )
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
