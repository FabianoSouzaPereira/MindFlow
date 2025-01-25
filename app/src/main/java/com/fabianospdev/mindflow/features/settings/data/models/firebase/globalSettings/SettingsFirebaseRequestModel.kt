package com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsRequestEntity

data class SettingsFirebaseRequestModel(
    override val content: Any
) : SettingsRequestEntity

