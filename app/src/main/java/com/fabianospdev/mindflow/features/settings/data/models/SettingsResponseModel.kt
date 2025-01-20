package com.fabianospdev.mindflow.features.settings.data.models

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity

class SettingsResponseModel(
    override val success: Boolean,
    override val message: String? = null,
    override val data: Any? = null
) : SettingsResponseEntity