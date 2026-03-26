package com.example.multimediaapp.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
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
/*
 * Esta clase gestiona la sesión del usuario en la aplicación.
 *
 * SessionManager actúa como un sistema de almacenamiento local
 * que permite guardar y recuperar datos del usuario incluso
 * después de cerrar la aplicación.
 *
 * Utiliza SharedPreferences:
 * - Es un almacenamiento clave-valor persistente en Android.
 * - Permite guardar información simple de forma local.
 *
 * CONTEXTO:
 * - Se recibe un Context para acceder a SharedPreferences.
 *
 * FUNCIONALIDADES PRINCIPALES:
 *
 * 1. saveUser(user):
 * - Guarda los datos del usuario en almacenamiento local.
 * - Se ejecuta al iniciar sesión o registrarse.
 * - Almacena:
 *   - id
 *   - email
 *   - name
 *   - lastName
 *   - country
 * - Se usa apply() para guardar de forma asíncrona.
 *
 * 2. getUser():
 * - Recupera los datos guardados del usuario.
 * - Devuelve un objeto UsersInfoDTO si existe sesión activa.
 * - Devuelve null si no hay datos almacenados.
 * - La contraseña (pass) no se guarda por seguridad.
 *
 * 3. logout():
 * - Elimina todos los datos del usuario.
 * - Cierra la sesión completamente.
 *
 * 4. isUserLoggedIn():
 * - Comprueba si existe una sesión activa.
 * - Se basa en la existencia del campo "id".
 *
 * SEGURIDAD:
 * - No se guarda la contraseña del usuario.
 * - Solo se almacenan datos necesarios para la sesión.
 *
 * USO:
 * - Mantener la sesión del usuario activa entre reinicios de la app.
 * - Evitar que el usuario tenga que iniciar sesión constantemente.
 *
 * VENTAJAS:
 * - Persistencia de datos local
 * - Simplicidad de uso
 * - Mejora la experiencia del usuario
 *
 * LIMITACIONES:
 * - No es seguro para datos sensibles como contraseñas.
 * - Solo debe usarse para datos básicos del usuario.
 */