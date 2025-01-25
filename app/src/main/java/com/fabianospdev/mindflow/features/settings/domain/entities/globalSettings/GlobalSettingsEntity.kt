package com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings

import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel

interface GlobalSettingsEntity {
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

fun GlobalSettingsEntity.copy(
    maintenanceMode: Boolean = this.maintenanceMode,
    defaultLanguage: String = this.defaultLanguage,
    privacyPolicyURL: String = this.privacyPolicyURL,
    termsOfServiceURL: String = this.termsOfServiceURL,
    appVersion: String = this.appVersion,
    featureToggle: Boolean = this.featureToggle,
    supportContactEmail: String = this.supportContactEmail,
    defaultTimezone: String = this.defaultTimezone,
    maxUploadSize: Long = this.maxUploadSize,
    analyticsEnabled: Boolean = this.analyticsEnabled,
    chatEnabled: Boolean = this.chatEnabled,
    darkMode: Boolean = this.darkMode
): GlobalSettingsEntity {
    return when (this) {
        is GlobalSettingsFirestoreModel -> GlobalSettingsFirestoreModel(
            maintenanceMode = maintenanceMode,
            defaultLanguage = defaultLanguage,
            privacyPolicyURL = privacyPolicyURL,
            termsOfServiceURL = termsOfServiceURL,
            appVersion = appVersion,
            featureToggle = featureToggle,
            supportContactEmail = supportContactEmail,
            defaultTimezone = defaultTimezone,
            maxUploadSize = maxUploadSize,
            analyticsEnabled = analyticsEnabled,
            chatEnabled = chatEnabled,
            darkMode = darkMode
        )

        is GlobalSettingsRelationalModel -> GlobalSettingsRelationalModel(
            maintenanceMode = maintenanceMode,
            defaultLanguage = defaultLanguage,
            privacyPolicyURL = privacyPolicyURL,
            termsOfServiceURL = termsOfServiceURL,
            appVersion = appVersion,
            featureToggle = featureToggle,
            supportContactEmail = supportContactEmail,
            defaultTimezone = defaultTimezone,
            maxUploadSize = maxUploadSize,
            analyticsEnabled = analyticsEnabled,
            chatEnabled = chatEnabled,
            darkMode = darkMode
        )

        else -> throw UnsupportedOperationException("Cópia não suportada para essa implementação")
    }
}
