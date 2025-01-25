package com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsRequestEntity
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class SettingsRelationalRequestModel(
    @Contextual
    override val content: Any
) : SettingsRequestEntity