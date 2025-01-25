package com.fabianospdev.mindflow.features.profile.presentation.ui.profile

import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError

sealed class ProfileError(message: String) : CommonError(message) {
    object DataLoadFailed : ProfileError("Failed to load data.")
    object SectionNotAvailable : ProfileError("Section not available.")
}