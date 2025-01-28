package com.fabianospdev.mindflow.features.login.data.datasources.remoto

import com.fabianospdev.mindflow.features.login.data.models.LoginRequestModel
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel
import retrofit2.Response
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val retrofitService: LoginDataSource
) : LoginDataSource {

    override suspend fun login(request: LoginRequestModel): Response<LoginResponseModel> {
        try {
            val isAdminClaim: Boolean = false
            val result = retrofitService.login(request)

            //           val idToken = result.user?.getIdToken(true)?.await()
//
//            idToken?.let {
//                val adminClaim = it.claims["admin"] as? Boolean
//                if (adminClaim == true) {
//                    Log.d("Auth", "User isn't admin")
//                } else {
//                    Log.d("Auth", "User is admin")
//                }
//
//                adminClaim?.let { it1 -> appConfig.saveAdminClaim(it1) }
//           }

            val loginResponse = LoginResponseModel(
                token = result.body()!!.token,
                isAdminClaim,
                result.body()?.photoUrl,
                result.body()?.uid,
                result.body()?.isEmailVerified,
                result.body()?.displayName
            )
            return Response.success(loginResponse)
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }
}
