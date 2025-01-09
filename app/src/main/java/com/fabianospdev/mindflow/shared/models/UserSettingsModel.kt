package com.fabianospdev.mindflow.shared.models

import com.fabianospdev.mindflow.shared.entities.UserSettingsEntity

data class UserSettingsModel(
    override val theme: String,
    override val notificationsEnabled: Boolean
) : UserSettingsEntity