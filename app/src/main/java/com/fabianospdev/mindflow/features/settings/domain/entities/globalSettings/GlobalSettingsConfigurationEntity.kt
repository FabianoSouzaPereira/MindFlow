package com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings

interface GlobalSettingsConfigurationEntity {
    val maintenanceMode: Boolean
    val defaultLanguage: String
    val privacyPolicyURL: String
    val termsOfServiceURL: String
    val appVersion: String
    val featureToggle: Boolean
    val supportContactEmail: String
    val defaultTimezone: String
    val maxUploadSize: Long
    val analyticsEnabled: Boolean
    val chatEnabled: Boolean
    val darkMode: Boolean
}