package com.example.multimediaapp.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.multimediaapp.model.UsersInfoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_session")

class DataStoreManager(private val context: Context) {

    private val ID = stringPreferencesKey("id")
    private val EMAIL = stringPreferencesKey("email")
    private val NAME = stringPreferencesKey("name")
    private val LASTNAME = stringPreferencesKey("lastName")
    private val COUNTRY = stringPreferencesKey("country")
    private val REMEMBER = booleanPreferencesKey("remember")

    val userFlow: Flow<UsersInfoDTO?> = context.dataStore.data.map { prefs ->
        val id = prefs[ID] ?: return@map null
        UsersInfoDTO(
            id = id,
            email = prefs[EMAIL] ?: "",
            pass = "", // no guardamos password
            name = prefs[NAME] ?: "",
            lastName = prefs[LASTNAME] ?: "",
            country = prefs[COUNTRY] ?: ""
        )
    }

    val rememberUserFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[REMEMBER] ?: false
    }

    suspend fun saveUser(user: UsersInfoDTO) {
        context.dataStore.edit { prefs ->
            prefs[ID] = user.id
            prefs[EMAIL] = user.email
            prefs[NAME] = user.name
            prefs[LASTNAME] = user.lastName
            prefs[COUNTRY] = user.country
        }
    }

    suspend fun saveUserEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL] = email
        }
    }

    suspend fun saveRememberUser(remember: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[REMEMBER] = remember
        }
    }

    suspend fun logout() {
        context.dataStore.edit { it.clear() }
    }
}