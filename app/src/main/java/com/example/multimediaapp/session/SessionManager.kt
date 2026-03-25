package com.example.multimediaapp.session

import android.content.Context
import com.example.multimediaapp.model.UsersInfoDTO

/**
 * SessionManager:
 * Clase encargada de gestionar la sesión del usuario en la aplicación.
 * Utiliza SharedPreferences para guardar datos del usuario de forma local
 * y mantener la sesión iniciada aunque se cierre la app.
 */
class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    /**
     * Guarda los datos del usuario al iniciar sesión o registrarse.
     */
    fun saveUser(user: UsersInfoDTO) {
        prefs.edit()
            .putString("id", user.id)
            .putString("email", user.email)
            .putString("name", user.name)
            .putString("lastName", user.lastName)
            .putString("country", user.country)
            .apply()
    }

    /**
     * Recupera los datos del usuario guardados.
     * Retorna null si no hay sesión activa.
     */
    fun getUser(): UsersInfoDTO? {
        val id = prefs.getString("id", null) ?: return null
        val email = prefs.getString("email", "") ?: ""
        val name = prefs.getString("name", "") ?: ""
        val lastName = prefs.getString("lastName", "") ?: ""
        val country = prefs.getString("country", "") ?: ""
        // La contraseña no se guarda por seguridad
        val pass = ""

        return UsersInfoDTO(
            id = id,
            email = email,
            pass = pass,
            name = name,
            lastName = lastName,
            country = country
        )
    }

    /**
     * Cierra la sesión eliminando todos los datos.
     */
    fun logout() {
        prefs.edit().clear().apply()
    }

    /**
     * Comprueba si hay una sesión activa.
     */
    fun isUserLoggedIn(): Boolean = prefs.contains("id")
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