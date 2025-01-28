package com.fabianospdev.mindflow.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey val id: Long,
    val maintenanceMode: Boolean,
    val defaultLanguage: String,
    val privacyPolicyURL: String,
    val termsOfServiceURL: String,
    val appVersion: String,
    val featureToggle: Boolean,
    val serverAddress: ServerAddress,
    val supportContactEmail: String,
    val defaultTimezone: String,
    val maxUploadSize: Long,
    val analyticsEnabled: Boolean,
    val chatEnabled: Boolean,
    val darkMode: Boolean,
)