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
data class BandRoute (val bandId: String) : NavRoute {
    fun route() = "band/$bandId"
}

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
/*
 * Este archivo define todas las rutas de navegación de la aplicación
 * utilizando una interfaz sellada (sealed interface).
 *
 * sealed interface NavRoute:
 * - Permite agrupar todas las rutas en un único tipo seguro.
 * - Obliga a definir todas las rutas en este mismo archivo.
 * - Mejora el control y la seguridad en el sistema de navegación.
 *
 * @Serializable:
 * - Permite convertir las rutas a formato String o JSON.
 * - Es útil para trabajar con Navigation Compose y pasar datos entre pantallas.
 *
 * TIPOS DE RUTAS:
 *
 * 1. Rutas con parámetros (data class):
 * - LoginRoute(email: String)
 * - RegisterRoute(email: String, user: String)
 * - BandRoute(bandId: String)
 * - AddBandRoute(...)
 *
 * Estas rutas contienen información que se necesita en la pantalla.
 * Se usan data class porque:
 * - Permiten comparar rutas correctamente
 * - Generan automáticamente métodos útiles (equals, hashCode, toString, copy)
 * - Son ideales para transportar datos
 *
 * 2. Rutas sin parámetros (object):
 * - LoginRegRoute
 * - MainRoute
 * - UserInfoRoute
 * - DialogRoute
 * - SettingsRoute
 *
 * Se usan object porque:
 * - No contienen datos
 * - Solo existe una única instancia
 * - Son más eficientes en memoria
 *
 * DIFERENCIA IMPORTANTE:
 * - data class → para rutas con datos
 * - object → para rutas sin datos
 *
 * BandRoute:
 * - Además de ser una ruta con parámetro, incluye una función route()
 *   que construye la ruta en formato String.
 * - Esto se usa para navegar pasando el bandId dinámicamente.
 *
 * IMPORTANCIA EN NAVIGATION:
 * - Este enfoque evita errores de escritura en rutas (strings hardcodeados).
 * - Mejora la mantenibilidad del código.
 * - Permite un tipado seguro en toda la navegación.
 *
 * CONCLUSIÓN:
 * Este archivo centraliza todas las rutas de navegación,
 * separando claramente rutas con datos y sin datos,
 * lo que mejora la estructura, seguridad y escalabilidad de la app.
 */