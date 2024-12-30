package com.fabianospdev.mindflow.features.login.data.models

import com.fabianospdev.mindflow.features.login.domain.entities.LoginRequestEntity
import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("email")
    override val email: String,
    @SerializedName("password")
    override val password: String
) : LoginRequestEntity