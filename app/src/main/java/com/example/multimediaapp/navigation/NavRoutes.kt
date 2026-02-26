package com.example.multimediaapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute

@Serializable
data class LoginRoute(val email: String) : NavRoute

@Serializable
data class RegisterRoute(val email: String, val user: String) : NavRoute

@Serializable
object LoginRegRoute : NavRoute

@Serializable
object MainPageRoute : NavRoute

@Serializable
object BandPageRoute : NavRoute

@Serializable
object UserInfoRoute : NavRoute

@Serializable
object DialogPageRoute : NavRoute

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