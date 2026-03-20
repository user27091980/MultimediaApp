package com.example.multimediaapp.navigation

import kotlinx.serialization.Serializable

/**
 * Interfaz sellada que representa TODAS las rutas posibles
 * de la aplicación.
 *
 * sealed → obliga a que todas las implementaciones estén
 * en este mismo archivo (más seguro y controlado).
 *
 * Serializable → permite que la ruta pueda convertirse
 * a String para usarla en Navigation Compose.
 */
/**
 * Base sealed interface para todas las rutas
 */
@Serializable
sealed interface NavRoute

// ----------------------
// RUTAS CON PARÁMETROS
// ----------------------

@Serializable
data class LoginRoute(
    val email: String? = null
) : NavRoute

@Serializable
data class RegisterRoute(
    val email: String? = null,
    val user: String? = null
) : NavRoute

@Serializable
data class BandRoute(
    val bandId: String
) : NavRoute {
    fun route() = "band/$bandId"
}

// ----------------------
// RUTAS SIN PARÁMETROS
// ----------------------

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
 * ===APUNTES====
 * /*emplear data class para rutas con parámetros:
 * Kotlin NO genera automáticamente:
 * equals()
 * hashCode()
 * toString()
 * copy()
 * destructuring (component1())
 * Eso significa que dos rutas con el mismo contenido NO son iguales
 * en Resumen:
 *
 * class → algo con lógica, identidad propia
 * data class → solo datos
 *
 * En navegación:
 * Rutas con parámetros → data class
 * Rutas sin parámetros → object
 *
 * object lo ponemos si es estático
 * no tiene sentido que tengamos clases vacias, es algo innecesario
 *
 */