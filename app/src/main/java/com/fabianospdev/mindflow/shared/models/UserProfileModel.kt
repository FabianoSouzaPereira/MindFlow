package com.fabianospdev.mindflow.shared.models

import com.fabianospdev.mindflow.shared.entities.UserProfileEntity

data class UserProfileModel(
    override val firstName: String,
    override val lastName: String,
    override val avatarUrl: String? = null
) : UserProfileEntity
