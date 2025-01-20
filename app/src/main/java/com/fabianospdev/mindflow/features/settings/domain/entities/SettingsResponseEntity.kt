package com.fabianospdev.mindflow.features.settings.domain.entities

interface SettingsResponseEntity {
    val success: Boolean
    val message: String?
    val data: Any?
}