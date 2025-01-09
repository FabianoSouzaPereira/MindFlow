package com.fabianospdev.mindflow.features.home.presentation.ui.home

import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError

sealed class HomeError(message: String) : CommonError(message) {
    object DataLoadFailed : HomeError("Failed to load data.")
    object SectionNotAvailable : HomeError("Section not available.")
}