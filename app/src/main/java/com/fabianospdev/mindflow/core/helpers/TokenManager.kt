package com.fabianospdev.mindflow.core.helpers
/*
* Advantages of this approach:
* Encapsulation: Access to the token is restricted to this TokenManager class, which makes the code more secure.
* Ease of maintenance: If you need to modify the way the token is saved or retrieved, simply change the implementation within this class.
* Testability: Since token handling is centralized, it is easier to create unit tests to ensure that the class's behavior is correct.
*/

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.fabianospdev.mindflow.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

object TokenManager {
    private val TOKEN_KEY = stringPreferencesKey("token")
    private const val TAG = "TokenManager"

    private fun getTokenFlow(context: Context): Flow<String?> {
        return context.dataStore.data
            .catch { exception ->
                Log.e(TAG, "Error accessing DataStore", exception)
                emit(emptyPreferences()) // Avoids crash and returns default values
            }
            .map { preferences ->
                preferences[TOKEN_KEY]
            }
    }

    suspend fun saveToken(context: Context, token: String) {
        try {
            context.dataStore.edit { preferences ->
                preferences[TOKEN_KEY] = token
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error saving token", e)
        }
    }

    suspend fun getToken(context: Context): String? {
        return try {
            getTokenFlow(context).firstOrNull()
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving token", e)
            null
        }
    }
}
