package com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record

import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError

sealed class EmotionRecordError(message: String) : CommonError(message) {
    object DataLoadFailed : EmotionRecordError("Failed to load data.")
    object SectionNotAvailable : EmotionRecordError("Section not available.")
}