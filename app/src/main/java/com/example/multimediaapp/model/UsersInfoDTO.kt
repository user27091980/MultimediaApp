package com.example.multimediaapp.model

/**
 * Data class que representa la información detallada de un usuario.
 *
 * Esta información se puede usar en la pantalla de perfil o configuración de usuario.
 * Todos los campos son inmutables (`val`) para evitar modificaciones accidentales.
 */
data class UsersInfoDTO(
    val id : String,
    val email: String,
    val pass: String,
    val name: String,
    val lastName: String,
    val country: String
)

/*====APUNTES====


Todos los campos son val:inmutabilidad, seguridad y consistencia.

id: usado para identificar usuarios en listas, repositorios o rutas.

email: clave para login o contacto.

user: nombre que se mostrará en la UI.

pass: contraseña (en producción nunca guardes texto plano, siempre hashea).
 */
/*
 * Este archivo define el modelo UsersInfoDTO, que representa la información
 * completa de un usuario dentro de la aplicación.
 *
 * Este modelo se utiliza principalmente en la capa de dominio y puede
 * ser empleado en pantallas como perfil de usuario o configuración.
 *
 * Contiene los siguientes datos:
 * - id: identificador único del usuario
 * - email: correo electrónico del usuario
 * - pass: contraseña del usuario
 * - name: nombre del usuario
 * - lastName: apellido del usuario
 * - country: país del usuario
 *
 * Este objeto actúa como un Data Transfer Object (DTO), es decir,
 * un contenedor de datos utilizado para transportar información entre
 * las distintas capas de la aplicación (API, repositorio, UI).
 *
 * Ventajas de este modelo:
 * - Mantiene la separación de responsabilidades
 * - Permite independencia respecto a la estructura de la API
 * - Facilita el mantenimiento y escalabilidad del código
 * - Controla qué datos se exponen en la aplicación
 *
 * Al ser una data class de Kotlin:
 * - Se generan automáticamente métodos como equals(), hashCode() y toString()
 * - Se puede usar el método copy() para crear nuevas instancias modificadas
 *
 * Todos los campos están definidos como val, lo que garantiza que el objeto
 * sea inmutable y evita modificaciones accidentales.
 *
 * Nota importante de seguridad:
 * En entornos reales, la contraseña nunca debería almacenarse en texto plano.
 * Siempre debe enviarse y almacenarse de forma cifrada o hasheada.
 */