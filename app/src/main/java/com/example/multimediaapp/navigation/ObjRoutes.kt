package com.example.multimediaapp.navigation

/**
 * ObjRoutes:
 *
 * Objeto que centraliza **todas las rutas de navegación** de la aplicación.
 *
 * Usamos `object` porque:
 * - Solo necesitamos una **instancia única**.
 * - No mantiene estado, solo actúa como contenedor de constantes.
 * - Facilita la **mantenibilidad** y evita repetir literales en todo el código.
 *
 * Contiene dos tipos de rutas:
 * 1. **Rutas sin parámetros** → se usan directamente.
 * 2. **Rutas con parámetros** → usan placeholders que se reemplazan al navegar.
 */
object ObjRoutes {

    // ================================
    // RUTAS SIN PARÁMETROS
    // ================================

    /** Pantalla inicial Login / Register */
    const val LOGINREG = "LoginRegRoute"

    /** Pantalla principal después de login */
    const val MAIN = "MainRoute"

    /** Pantalla de información del usuario */
    const val INFOUSER = "UserInfoRoute"

    /** Pantalla de configuración */
    const val SETTINGS = "SettingsRoute"

    /** Pantalla relacionada con bandas (detalle de banda) */
    const val BAND = "BandRoute"

    /** Diálogo de registro */
    const val DIALOG = "DialogRoute"

    /** Pantalla para agregar una nueva banda */
    const val ADDBAND = "AddBandRoute"


    // ================================
    // RUTAS CON PARÁMETROS
    // ================================

    /**
     * Ruta de Login que recibe parámetros:
     * - {email}: email del usuario
     * - {passwd}: contraseña
     *
     * Los placeholders {email} y {passwd} se reemplazan al navegar.
     *
     * Ejemplo:
     * LoginScreen("correo@mail.com", "1234")
     * → "LoginRoute/correo@mail.com/1234"
     */
    const val LOGIN = "LoginRoute/{email}/{passwd}"

    /**
     * Ruta de Register que recibe parámetros:
     * - {email}: email del usuario
     * - {name}: nombre de usuario
     *
     * Ejemplo:
     * RegisterScreen("correo@mail.com", "Juan")
     * → "RegisterRoute/correo@mail.com/Juan"
     */
    const val REGISTER = "RegisterRoute/{email}/{name}"

    // ================================
    // NOTAS ADICIONALES
    // ================================
    /**
     * 1. Para navegar con parámetros:
     *    - Reemplazar los placeholders con los valores reales.
     *    - Ejemplo: ObjRoutes.LOGIN.replace("{email}", email).replace("{passwd}", passwd)
     *
     * 2. Rutas sin parámetros:
     *    - Se usan directamente como constantes.
     *
     * 3. Centralizar rutas:
     *    - Facilita refactorización.
     *    - Evita errores de typo en strings de rutas.
     */
}