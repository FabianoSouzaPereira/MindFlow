package com.fabianospdev.mindflow.features.login.data.models

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity
import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("token") override val token: String
) : LoginResponseEntity
