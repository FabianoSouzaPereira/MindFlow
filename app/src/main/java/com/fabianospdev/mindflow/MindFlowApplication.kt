package com.fabianospdev.mindflow

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MindFlowApplication : Application()

// DataStore correctly defined as an extension of Context
val Context.dataStore by preferencesDataStore(name = "settings")

object PreferencesKeys {
    val TOKEN_KEY = booleanPreferencesKey("token")
    val DARKMODE = booleanPreferencesKey("darkMode")
}