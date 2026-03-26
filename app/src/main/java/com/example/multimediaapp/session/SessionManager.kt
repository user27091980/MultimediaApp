package com.example.multimediaapp.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.multimediaapp.model.UsersInfoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_session")

class SessionManager(private val context: Context) {

    private val ID = stringPreferencesKey("id")
    private val EMAIL = stringPreferencesKey("email")
    private val NAME = stringPreferencesKey("name")
    private val LASTNAME = stringPreferencesKey("lastName")
    private val COUNTRY = stringPreferencesKey("country")

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

    suspend fun saveUser(user: UsersInfoDTO) {
        context.dataStore.edit { prefs ->
            prefs[ID] = user.id
            prefs[EMAIL] = user.email
            prefs[NAME] = user.name
            prefs[LASTNAME] = user.lastName
            prefs[COUNTRY] = user.country
        }
    }

    suspend fun logout() {
        context.dataStore.edit { it.clear() }
    }
}
/**
 * SessionManager usando DataStore para manejar la sesión del usuario.
 *
 * Esta clase reemplaza el antiguo SharedPreferences con DataStore, que es más moderno,
 * seguro y soporta programación reactiva mediante Flows.
 *
 * Funciones principales:
 *
 * 1. saveUser(user: UsersInfoDTO)
 *    - Guarda los datos del usuario (id, email, nombre, apellido, país) en DataStore.
 *    - Se ejecuta dentro de una función suspend porque DataStore es asíncrono.
 *
 * 2. userFlow: Flow<UsersInfoDTO?>
 *    - Expone los datos del usuario como un Flow.
 *    - Permite que la UI se actualice automáticamente si cambian los datos del usuario.
 *    - Retorna null si no hay usuario guardado.
 *
 * 3. getUser(): UsersInfoDTO?
 *    - Obtiene los datos del usuario de forma puntual (una sola vez).
 *    - Devuelve null si no hay sesión activa.
 *
 * 4. logout()
 *    - Limpia todos los datos de la sesión, cerrando efectivamente la sesión del usuario.
 *
 * 5. isUserLoggedIn: Flow<Boolean>
 *    - Devuelve un Flow que indica si hay sesión activa (true si hay id guardado).
 *    - Útil para mostrar UI condicional según el estado de login.
 *
 * Beneficios de usar DataStore:
 * - Operaciones asíncronas y seguras.
 * - Soporte para programación reactiva con Flows.
 * - Evita problemas de concurrencia que ocurren con SharedPreferences.
 *
 * Nota de seguridad:
 * - La contraseña del usuario no se guarda en DataStore.
 * - Solo se almacena información básica para recordar la sesión.
 */