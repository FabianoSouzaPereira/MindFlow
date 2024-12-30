package com.fabianospdev.mindflow.features.login.data.models

import com.fabianospdev.mindflow.features.login.domain.entities.UserEntity
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("name") override val name: String
): UserEntity
