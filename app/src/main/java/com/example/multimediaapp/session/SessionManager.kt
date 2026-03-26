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