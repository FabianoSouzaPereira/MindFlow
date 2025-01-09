package com.fabianospdev.mindflow.features.login.data.models

import com.fabianospdev.mindflow.features.login.domain.entities.LoginUserEntity
import com.google.gson.annotations.SerializedName

data class LoginUserModel(
    @SerializedName("name") override val name: String
): LoginUserEntity
