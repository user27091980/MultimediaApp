package com.example.multimediaapp.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.multimediaapp.model.LoginDTO
import com.example.multimediaapp.model.UsersInfoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStoreManager:
 *
 * Clase que centraliza la gestión de la sesión de usuario
 * usando Jetpack DataStore (Preferences).
 *
 * Responsabilidades principales:
 * 1. Guardar información del usuario de forma persistente.
 * 2. Proporcionar flujos reactivos (`Flow`) para observar cambios en la sesión.
 * 3. Manejar la opción "Recordar usuario" sin guardar contraseñas.
 * 4. Permitir cerrar sesión limpiando los datos.
 *
 * ===Notas de implementación===
 * - `preferencesDataStore(name)` crea un DataStore llamado "user_session".
 * - `stringPreferencesKey` y `booleanPreferencesKey` definen claves para almacenar datos.
 * - Todos los datos guardados son inmutables desde la UI, se acceden mediante `Flow`.
 * - No se almacena la contraseña por seguridad.
 *
 * ===Propiedades importantes===
 * - `userFlow: Flow<UsersInfoDTO?>`
 *   Flujo reactivo que emite la información del usuario si existe, o null si no hay sesión.
 *
 * - `rememberUserFlow: Flow<Boolean>`
 *   Flujo reactivo que indica si el usuario pidió recordar sesión.
 *
 * ===Funciones principales===
 * - `saveUser(user: UsersInfoDTO)`: Guarda datos principales del usuario (id, nombre, email, país, apellido).
 * - `saveUserEmail(email: String)`: Guarda solo el email.
 * - `saveRememberUser(remember: Boolean)`: Marca si se debe recordar el usuario.
 * - `logout()`: Limpia todos los datos guardados.
 *
 * ===Ejemplo de uso===
 * ```
 * val dataStoreManager = DataStoreManager(context)
 *
 * // Guardar usuario
 * dataStoreManager.saveUser(userDto)
 *
 * // Observar cambios en la sesión
 * dataStoreManager.userFlow.collect { user ->
 *     if (user != null) showMainScreen(user)
 * }
 *
 * // Recordar usuario
 * dataStoreManager.saveRememberUser(true)
 *
 * // Cerrar sesión
 * dataStoreManager.logout()
 * ```
 *
 * Ventajas:
 * - Persistencia segura y eficiente.
 * - Flujos reactivos fáciles de observar en Compose o ViewModel.
 * - Separación clara de la lógica de sesión del resto de la app.
 */

val Context.dataStore by preferencesDataStore(name = "user_session")

class DataStoreManager(private val context: Context) {

    private val ID = stringPreferencesKey("id")

    private val EMAIL = stringPreferencesKey("email")
    private val NAME = stringPreferencesKey("name")
    private val LASTNAME = stringPreferencesKey("lastName")
    private val COUNTRY = stringPreferencesKey("country")
    private val REMEMBER = booleanPreferencesKey("remember")
    private val DARK_MODE = booleanPreferencesKey("dark_mode")

    // Flujo reactivo que emite los datos del usuario
    val userFlow: Flow<UsersInfoDTO?> = context.dataStore.data.map { prefs ->
        val id = prefs[ID] ?: return@map null
        UsersInfoDTO(
            id = id,
            email = prefs[EMAIL] ?: "",
            passwd = "", // nunca guardamos contraseña
            name = prefs[NAME] ?: "",
            lastName = prefs[LASTNAME] ?: "",
            country = prefs[COUNTRY] ?: "",

        )
    }

    // Flujo reactivo que indica si recordar sesión está activo
    val rememberUserFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[REMEMBER] ?: false
    }

    // Guardar información completa del usuario
    suspend fun saveUser(user: LoginDTO) {
        context.dataStore.edit { prefs ->
            prefs[ID] = user.id
            prefs[EMAIL] = user.email
            prefs[NAME] = user.name
            // Opcional: limpiar apellidos/país si venían de un registro previo
            prefs[LASTNAME] = ""
            prefs[COUNTRY] = ""
        }
    }


    // Guardar solo email
    // Solo guarda el email para la pantalla de Login
    suspend fun saveUserEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL] = email
        }
    }

    // Guarda si el checkbox estaba marcado
    suspend fun saveRememberUser(remember: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[REMEMBER] = remember
        }
    }

    // Cerrar sesión
    suspend fun logout() {
        context.dataStore.edit { prefs ->
            prefs.remove(ID)
            prefs.remove(EMAIL)
            prefs.remove(NAME)
            prefs[REMEMBER] = false // <--- Al cerrar sesión, ya no recordamos
        }
    }



    private fun darkModeKey(userId: String) = booleanPreferencesKey("dark_mode_$userId")

    // Flujo que observa el modo oscuro del usuario actual
    fun getDarkModeFlow(userId: String): Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[darkModeKey(userId)] ?: false
    }

    // Guarda el modo oscuro para el usuario actual
    suspend fun saveDarkMode(userId: String, enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[darkModeKey(userId)] = enabled
        }
    }

}