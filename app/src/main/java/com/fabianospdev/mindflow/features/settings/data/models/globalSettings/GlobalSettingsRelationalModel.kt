package com.fabianospdev.mindflow.features.settings.data.models.globalSettings

import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.firebase.GlobalSettingsFirestoreEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GlobalSettingsRelationalModel(
    @SerialName("maintenanceMode")
    override val maintenanceMode: Boolean,

    @SerialName("defaultLanguage")
    override val defaultLanguage: String,

    @SerialName("privacyPolicyURL")
    override val privacyPolicyURL: String,

    @SerialName("termsOfServiceURL")
    override val termsOfServiceURL: String,

    @SerialName("appVersion")
    override val appVersion: String,

    @SerialName("featureToggle")
    override val featureToggle: Boolean,

    @SerialName("supportContactEmail")
    override val supportContactEmail: String,

    @SerialName("defaultTimezone")
    override val defaultTimezone: String,

    @SerialName("maxUploadSize")
    override val maxUploadSize: Long,

    @SerialName("analyticsEnabled")
    override val analyticsEnabled: Boolean,

    @SerialName("chatEnabled")
    override val chatEnabled: Boolean,

    @SerialName("darkMode")
    override val darkMode: Boolean
) : GlobalSettingsFirestoreEntity
