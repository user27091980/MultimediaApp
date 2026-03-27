package com.example.multimediaapp.navigation

import kotlinx.serialization.Serializable

/**
 * NavRoute:
 *
 * Interfaz sellada que representa **todas las rutas posibles** de la aplicación.
 *
 * Características:
 * - `sealed interface` → garantiza que todas las rutas estén definidas en este archivo.
 *   Esto permite control total sobre la navegación y evita rutas "desconocidas".
 * - `@Serializable` → permite serializar/deserializar la ruta a String o JSON,
 *   útil para pasar parámetros entre pantallas con Navigation Compose.
 */
@Serializable
sealed interface NavRoute

/**
 * Rutas con parámetros:
 *
 * Usamos `data class` porque contienen información variable.
 * Kotlin genera automáticamente:
 * - equals()        → para comparar rutas
 * - hashCode()      → útil en colecciones
 * - toString()      → depuración
 * - copy()          → clonar con cambios
 * - componentN()    → destructuring
 *
 * Esto asegura que la navegación funcione correctamente cuando se comparan rutas.
 */

/** Ruta de login que recibe un email como parámetro */
@Serializable
data class LoginRoute(val email: String) : NavRoute

/** Ruta de registro que recibe email y nombre de usuario */
@Serializable
data class RegisterRoute(val email: String, val user: String) : NavRoute

/** Ruta de detalles de banda con ID dinámico */
data class BandRoute(val bandId: String) : NavRoute {
    /** Función auxiliar para construir la ruta como String para Navigation */
    fun route() = "band/$bandId"
}

/** Ruta para agregar una banda con todos sus campos como parámetros */
data class AddBandRoute(
    val id: String,
    val name: String,
    val description: String,
    val banner: String,
    val albumImages: List<String>,
    val style: String,
    val recordLabel: String,
    val components: String,
    val albumLinks: List<String>,
    val headerLink: String
) : NavRoute

/**
 * Rutas estáticas (sin parámetros):
 *
 * Usamos `object` porque:
 * - No contienen datos dinámicos
 * - Solo necesitamos una instancia única
 * - Son más eficientes que clases vacías
 */
@Serializable
object LoginRegRoute : NavRoute

@Serializable
object MainRoute : NavRoute

@Serializable
object UserInfoRoute : NavRoute

@Serializable
object DialogRoute : NavRoute

@Serializable
object SettingsRoute : NavRoute

/**
 * ===APUNTES ADICIONALES====
 *
 * 1. Rutas con parámetros → `data class`
 *    - Permite comparar correctamente rutas con diferentes contenidos.
 * 2. Rutas sin parámetros → `object`
 *    - Evita crear instancias innecesarias
 *    - Representa rutas estáticas únicas
 * 3. Evitar `class` vacías para rutas:
 *    - No tiene sentido, genera instancias innecesarias y no aporta ventajas
 */