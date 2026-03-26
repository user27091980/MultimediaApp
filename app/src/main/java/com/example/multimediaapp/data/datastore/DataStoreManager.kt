package com.example.multimediaapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStoreManager: gestiona los datos del usuario de forma segura.
 * Guarda solo nombre y email, no guarda contraseña.
 */
class DataStoreManager(private val context: Context) {

    // Nombre del DataStore
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    // Keys
    private val NAME_KEY = stringPreferencesKey("user_name")
    private val EMAIL_KEY = stringPreferencesKey("user_email")

    /**
     * Guardar usuario (solo nombre y email)
     */
    suspend fun saveUser(name: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME_KEY] = name
            prefs[EMAIL_KEY] = email
        }
    }

    /**
     * Recuperar nombre
     */
    val getName: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[NAME_KEY] ?: "" }

    /**
     * Recuperar email
     */
    val getEmail: Flow<String> = context.dataStore.data
        .map { prefs -> prefs[EMAIL_KEY] ?: "" }

    /**
     * Verifica si hay un usuario logueado
     */
    val isLogged: Flow<Boolean> = context.dataStore.data
        .map { prefs ->
            val email = prefs[EMAIL_KEY] ?: ""
            email.isNotBlank()
        }

    /**
     * Logout: limpia datos
     */
    suspend fun logout() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}