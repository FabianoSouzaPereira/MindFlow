package com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings

import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity

data class GlobalSettingsFirestoreModel(
    override val maintenanceMode: Boolean = false,
    override val defaultLanguage: String = "",
    override val privacyPolicyURL: String = "",
    override val termsOfServiceURL: String = "",
    override val appVersion: String = "",
    override val featureToggle: Boolean = false,
    override val supportContactEmail: String = "",
    override val defaultTimezone: String = "",
    override val maxUploadSize: Long = 0L,
    override val analyticsEnabled: Boolean = false,
    override val chatEnabled: Boolean = false,
    override val darkMode: Boolean = false
) : GlobalSettingsEntity