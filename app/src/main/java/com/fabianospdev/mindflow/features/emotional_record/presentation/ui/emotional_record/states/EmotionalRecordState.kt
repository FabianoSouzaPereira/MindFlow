package com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record.states

import com.fabianospdev.mindflow.features.emotional_record.domain.entities.EmotionalRecordResponseEntity

sealed class EmotionalRecordState {
    object EmotionalRecordLoading : EmotionalRecordState()

    object EmotionalRecordIdle : EmotionalRecordState()

    data class EmotionalRecordSuccess(val response: EmotionalRecordResponseEntity) : EmotionalRecordState()

    data class EmotionalRecordError(val error: String) : EmotionalRecordState() {
        fun isNetworkRelated(): Boolean {
            return error.contains("network", ignoreCase = true)
        }
    }

    data class EmotionalRecordNoConnection(val errorMessage: String) : EmotionalRecordState()

    data class EmotionalRecordValidationError(val message: String) : EmotionalRecordState()

    data class EmotionalRecordTimeoutError(val message: String) : EmotionalRecordState()

    data class EmotionalRecordUnauthorized(val message: String) : EmotionalRecordState()

    data class EmotionalRecordUnknown(val message: String) : EmotionalRecordState() {
        fun isEmotionalRecordUnknown(): Boolean {
            return message.isNotEmpty()
        }

        override fun toString(): String {
            return "Error unknown occurred with message: $message"
        }
    }
}