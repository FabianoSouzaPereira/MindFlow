package com.fabianospdev.mindflow.core.helpers

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppConfig(initialState: Boolean, private val context: Context) {
    private val _isUsingFirebaseStateFlow = MutableStateFlow(initialState)
    val isUsingFirebase: StateFlow<Boolean> get() = _isUsingFirebaseStateFlow

    fun setUsingFirebase(isUsing: Boolean) {
        _isUsingFirebaseStateFlow.value = isUsing
    }

    fun saveAdminClaim(adminClaim: Boolean) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("is_using_firebase", adminClaim).apply()
    }

    fun getAdminClaim(): Boolean {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("adminClaim", false)
    }

    private fun saveState(isUsing: Boolean) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("is_using_firebase", isUsing).apply()
    }
}