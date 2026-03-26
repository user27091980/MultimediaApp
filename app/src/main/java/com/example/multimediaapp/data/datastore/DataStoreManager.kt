package com.example.multimediaapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

/**
 * DataStoreManager: gestiona los datos del usuario de forma segura.
 * Guarda solo nombre y email, no guarda contraseña.
 */
val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {

    private val NAME_KEY = stringPreferencesKey("user_name")
    private val EMAIL_KEY = stringPreferencesKey("user_email")
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    suspend fun saveUser(name: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME_KEY] = name
            prefs[EMAIL_KEY] = email
        }
    }

    val getName = context.dataStore.data.map { prefs -> prefs[NAME_KEY] ?: "" }
    val getEmail = context.dataStore.data.map { prefs -> prefs[EMAIL_KEY] ?: "" }
    val isLogged = context.dataStore.data.map { prefs -> (prefs[EMAIL_KEY] ?: "").isNotBlank() }
    // Guardar modo oscuro
    suspend fun saveDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = isDarkMode
        }
    }

    // Recuperar modo oscuro
    val getDarkMode = context.dataStore.data
        .map { prefs -> prefs[DARK_MODE_KEY] ?: false }
    suspend fun logout() {
        context.dataStore.edit { it.clear() }
    }
}