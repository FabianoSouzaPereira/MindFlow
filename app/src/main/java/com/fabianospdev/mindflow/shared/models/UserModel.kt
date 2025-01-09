package com.fabianospdev.mindflow.shared.models

import com.fabianospdev.mindflow.shared.entities.UserEntity

data class UserModel(
    override val id: String,
    override val username: String,
    override val email: String,
    override val profile: UserProfileModel,
    override val settings: UserSettingsModel
) : UserEntity
