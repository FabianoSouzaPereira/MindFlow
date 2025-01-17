package com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record.states

class EmotionalRecordUnknown(val message: String) : EmotionalRecordState() {
    fun isEmotionalRecordUnknown(): Boolean {
        return message.isNotEmpty()
    }

    override fun toString(): String {
        return "Error unknown occurred with message: $message"
    }
}