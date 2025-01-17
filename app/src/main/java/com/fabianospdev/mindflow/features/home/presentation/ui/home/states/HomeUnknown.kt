package com.fabianospdev.mindflow.features.home.presentation.ui.home.states

data class HomeUnknown(val message: String) : HomeState() {

    fun isHomeUnknown(): Boolean {
        return message.isNotEmpty()
    }

    override fun toString(): String {
        return "Error unknown occurred with message: $message"
    }
}