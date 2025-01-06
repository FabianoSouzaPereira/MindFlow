package com.fabianospdev.mindflow.features.home.presentation.ui.home.states

data class HomeError(val error: String) : HomeState() {
    fun isNetworkRelated(): Boolean {
        return error.contains("network", ignoreCase = true)
    }
}