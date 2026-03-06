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
@Serializable
sealed interface NavRoute
/**
 * Ruta hacia Login que recibe parámetros.
 *
 * Se usa data class porque contiene datos (email).
 *
 * data class genera automáticamente:
 * - equals()
 * - hashCode()
 * - toString()
 * - copy()
 * - componentN()
 *
 * Esto es importante porque Navigation compara rutas.
 */

@Serializable
data class LoginRoute(val email: String) : NavRoute

/**
 * Ruta hacia Register con parámetros.
 *
 * Como tiene datos (email y user),
 * usamos data class.
 */
@Serializable
data class RegisterRoute(val email: String, val user: String) : NavRoute

/**
 * Ruta estática (sin parámetros).
 *
 * Se usa object porque:
 * - No contiene datos
 * - Solo existe una instancia
 * - Es más eficiente
 */
@Serializable
object LoginRegRoute : NavRoute

@Serializable
object MainRoute : NavRoute

@Serializable
object BandRoute : NavRoute

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