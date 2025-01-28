package com.fabianospdev.mindflow.features.login.data.models

import android.net.Uri
import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("token") override val token: String,
    @SerializedName("adminClaim") override val adminClaim: Boolean? = false,
    @SerializedName("photoUrl") override val photoUrl: Uri? = Uri.parse("https://default.url/image.jpg"),
    @SerializedName("uid") override val uid: String? = "",
    @SerializedName("isEmailVerified") override val isEmailVerified: Boolean? = false,
    @SerializedName("displayName") override val displayName: String? = "",
) : LoginResponseEntity