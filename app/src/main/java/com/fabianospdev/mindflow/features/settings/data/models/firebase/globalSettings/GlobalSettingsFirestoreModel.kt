package com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings

import com.fabianospdev.mindflow.core.database.entities.Settings
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity

data class GlobalSettingsFirestoreModel(
    override val id: Long = 0,
    override val maintenanceMode: Boolean = false,
    override val defaultLanguage: String = "",
    override val privacyPolicyURL: String = "",
    override val termsOfServiceURL: String = "",
    override val appVersion: String = "",
    override val featureToggle: Boolean = false,
    override val serverAddress: ServerAddressFirebaseModel = ServerAddressFirebaseModel(),
    override val supportContactEmail: String = "",
    override val defaultTimezone: String = "",
    override val maxUploadSize: Long = 0L,
    override val analyticsEnabled: Boolean = false,
    override val chatEnabled: Boolean = false,
    override val darkMode: Boolean = false
) : GlobalSettingsEntity

fun GlobalSettingsFirestoreModel.toEntity(): Settings {
    return Settings(
        id = this.id,
        maintenanceMode = this.maintenanceMode,
        defaultLanguage = this.defaultLanguage,
        privacyPolicyURL = this.privacyPolicyURL,
        termsOfServiceURL = this.termsOfServiceURL,
        appVersion = this.appVersion,
        featureToggle = this.featureToggle,
        serverAddress = this.serverAddress.toEntity(),
        supportContactEmail = this.supportContactEmail,
        defaultTimezone = this.defaultTimezone,
        maxUploadSize = this.maxUploadSize,
        analyticsEnabled = this.analyticsEnabled,
        chatEnabled = this.chatEnabled,
        darkMode = this.darkMode
    )
}