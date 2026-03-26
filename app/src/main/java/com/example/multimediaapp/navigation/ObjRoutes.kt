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


/*
 * Este objeto centraliza todas las rutas de navegación de la aplicación.
 *
 * Se utiliza un object porque:
 * - Solo se necesita una única instancia global
 * - No contiene estado mutable
 * - Funciona como un contenedor de constantes
 * - Facilita el acceso y la organización de las rutas
 *
 * Las rutas se dividen en dos tipos:
 *
 * 1. RUTAS SIN PARÁMETROS:
 * - LOGINREG: pantalla inicial de login/registro
 * - MAIN: pantalla principal de la aplicación
 * - INFOUSER: información del usuario
 * - SETTINGS: configuración de la aplicación
 * - BAND: pantalla de detalle de banda
 * - DIALOG: pantalla tipo diálogo
 * - ADDBAND: pantalla para añadir una banda
 *
 * Estas rutas son constantes simples de tipo String y no requieren datos adicionales.
 *
 * 2. RUTAS CON PARÁMETROS:
 *
 * - LOGIN: recibe email y contraseña
 *   Ejemplo de ruta:
 *   "LoginRoute/{email}/{passwd}"
 *
 * - REGISTER: recibe email y nombre
 *   Ejemplo de ruta:
 *   "RegisterRoute/{email}/{name}"
 *
 * Los parámetros entre {} son placeholders que se sustituyen dinámicamente
 * al navegar entre pantallas.
 *
 * IMPORTANTE:
 * - Estas rutas permiten pasar datos entre pantallas a través del NavController.
 * - Facilitan la navegación dinámica en la aplicación.
 *
 * FUNCIONAMIENTO:
 * - Las rutas con parámetros se construyen reemplazando los valores
 *   en tiempo de ejecución.
 * - Ejemplo:
 *   "LoginRoute/correo@mail.com/1234"
 *
 * BENEFICIOS:
 * - Evita errores por strings hardcodeados
 * - Centraliza todas las rutas en un solo lugar
 * - Mejora la mantenibilidad del código
 * - Hace la navegación más clara y organizada
 */