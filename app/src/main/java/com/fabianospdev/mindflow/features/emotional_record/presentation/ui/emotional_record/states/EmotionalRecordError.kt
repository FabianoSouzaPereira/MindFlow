package com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record.states

class EmotionalRecordError(val error: String) : EmotionalRecordState() {
    fun isNetworkRelated(): Boolean {
        return error.contains("network", ignoreCase = true)
    }
}