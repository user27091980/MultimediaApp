package com.example.multimediaapp.navigation

/**
 * Objeto que centraliza todas las rutas de navegación.
 *
 * Usamos object porque:
 * - Solo necesitamos una instancia
 * - No tiene estado
 * - Sirve como contenedor de constantes
 */
object ObjRoutes {


    //RUTAS SIN PARÁMETROS
    /**
     * Pantalla inicial Login / Register
     */
    const val LOGINREG = "LoginRegRoute"

    /**
     * Pantalla principal después de login
     */
    const val MAIN = "MainRoute"

    /**
     * Pantalla de información del usuario
     */
    const val INFOUSER = "UserInfoRoute"

    /**
     * Pantalla de configuración
     */
    const val SETTINGS = "SettingsRoute"

    /**
     * Pantalla relacionada con bandas
     */
    const val BAND = "BandRoute"
    const val DIALOG = "DialogRoute"

    // RUTAS CON PARÁMETROS
    /**
     * Ruta de Login que recibe:
     * - email
     * - contraseña
     *
     * {email} y {passwd} son placeholders
     * que luego se reemplazan al navegar.
     */
    const val LOGIN = "LoginRoute/{email}/{passwd}"
    /**
     * Función que construye la ruta real
     * sustituyendo los parámetros.
     *
     * Ejemplo:
     * LoginScreen("correo@mail.com", "1234")
     * → "LoginRoute/correo@mail.com/1234"
     */


    /**
     * Ruta de Register que recibe:
     * - email
     * - nombre
     */
    const val REGISTER = "RegisterRoute/{email}/{name}"
    /**
     * Construye la ruta real para navegación.
     *
     * Ejemplo:
     * RegisterScreen("correo@mail.com", "Juan")
     * → "RegisterRoute/correo@mail.com/Juan"
     */

}