package com.fabianospdev.mindflow.features.settings.presentation.ui.settings

import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError

sealed class SettingsError(message: String) : CommonError(message) {
    object DataLoadFailed : SettingsError("Failed to load data.")
    object SectionNotAvailable : SettingsError("Section not available.")
}
