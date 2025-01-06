package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states

data class SettingsUnknown(val message: String): SettingsState() {

    fun isLoginUnknown() : Boolean {
        return message.isNotEmpty()
    }

    override fun toString(): String {
        return "Error unknown occurred with message: $message"
    }
}