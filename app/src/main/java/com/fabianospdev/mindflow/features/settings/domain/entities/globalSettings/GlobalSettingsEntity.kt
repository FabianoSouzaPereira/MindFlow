package com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings

import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.ServerAddressFirebaseModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.ServerAddressRelationalModel

interface GlobalSettingsEntity {
    val id: Long
    val maintenanceMode: Boolean
    val defaultLanguage: String
    val privacyPolicyURL: String
    val termsOfServiceURL: String
    val appVersion: String
    val featureToggle: Boolean
    val serverAddress: ServerAddressEntity
    val supportContactEmail: String
    val defaultTimezone: String
    val maxUploadSize: Long
    val analyticsEnabled: Boolean
    val chatEnabled: Boolean
    val darkMode: Boolean
}

fun GlobalSettingsEntity.copy(
    id: Long = this.id,
    maintenanceMode: Boolean = this.maintenanceMode,
    defaultLanguage: String = this.defaultLanguage,
    privacyPolicyURL: String = this.privacyPolicyURL,
    termsOfServiceURL: String = this.termsOfServiceURL,
    appVersion: String = this.appVersion,
    featureToggle: Boolean = this.featureToggle,
    serverAddress: ServerAddressEntity = this.serverAddress,
    supportContactEmail: String = this.supportContactEmail,
    defaultTimezone: String = this.defaultTimezone,
    maxUploadSize: Long = this.maxUploadSize,
    analyticsEnabled: Boolean = this.analyticsEnabled,
    chatEnabled: Boolean = this.chatEnabled,
    darkMode: Boolean = this.darkMode
): GlobalSettingsEntity {
    return when (this) {
        is GlobalSettingsFirestoreModel -> GlobalSettingsFirestoreModel(
            id = id,
            maintenanceMode = maintenanceMode,
            defaultLanguage = defaultLanguage,
            privacyPolicyURL = privacyPolicyURL,
            termsOfServiceURL = termsOfServiceURL,
            appVersion = appVersion,
            featureToggle = featureToggle,
            serverAddress = serverAddress as ServerAddressFirebaseModel,
            supportContactEmail = supportContactEmail,
            defaultTimezone = defaultTimezone,
            maxUploadSize = maxUploadSize,
            analyticsEnabled = analyticsEnabled,
            chatEnabled = chatEnabled,
            darkMode = darkMode
        )

        is GlobalSettingsRelationalModel -> GlobalSettingsRelationalModel(
            id = id,
            maintenanceMode = maintenanceMode,
            defaultLanguage = defaultLanguage,
            privacyPolicyURL = privacyPolicyURL,
            termsOfServiceURL = termsOfServiceURL,
            appVersion = appVersion,
            featureToggle = featureToggle,
            serverAddress = serverAddress as ServerAddressRelationalModel,
            supportContactEmail = supportContactEmail,
            defaultTimezone = defaultTimezone,
            maxUploadSize = maxUploadSize,
            analyticsEnabled = analyticsEnabled,
            chatEnabled = chatEnabled,
            darkMode = darkMode
        )

        else -> throw UnsupportedOperationException("Copy not supported for this implementation")
    }
}
