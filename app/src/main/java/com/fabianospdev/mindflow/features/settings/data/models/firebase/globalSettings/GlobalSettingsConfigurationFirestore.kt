package com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings

import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsConfigurationEntity
import kotlinx.serialization.Serializable

@Serializable
data class GlobalSettingsConfigurationFirestore(
    override val maintenanceMode: Boolean,
    override val defaultLanguage: String,
    override val privacyPolicyURL: String,
    override val termsOfServiceURL: String,
    override val appVersion: String,
    override val featureToggle: Boolean,
    override val supportContactEmail: String,
    override val defaultTimezone: String,
    override val maxUploadSize: Long,
    override val analyticsEnabled: Boolean,
    override val chatEnabled: Boolean,
    override val darkMode: Boolean
) : GlobalSettingsConfigurationEntity
