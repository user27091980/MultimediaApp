package com.example.multimediaapp.model

/**
 * Data class que representa la información de login que envía la UI
 * al servidor para autenticarse.
 *
 * LoginRequestDTO contiene solo los datos necesarios para el login.
 * Se usa `val` para mantener la inmutabilidad y evitar cambios accidentales.
 */
data class LoginRequestDTO(
    // Nombre de usuario o email
    val user: String,

    // Contraseña
    val pass: String
)

/**
 * Data class que representa la información de un usuario autenticado
 * recibida desde el servidor.
 *
 * LoginDTO se usa en la capa de dominio / ViewModel para manejar
 * la sesión del usuario y datos relacionados.
 *
 * Campos inmutables (`val`) para seguridad y consistencia.
 */
data class LoginDTO(
    // Identificador único del usuario
    val id: String,

    // Nombre de usuario
    val user: String,

    // Correo electrónico
    val email: String,

    // Contraseña (en aplicaciones reales se recomienda no exponerla)
    val pass: String
)

/**
 * EXPLICACIÓN:
 *
 * - ¿Por qué DTO?
 *   DTO (Data Transfer Object) permite transportar datos entre
 *   capas de manera desacoplada, sin exponer la implementación
 *   del API o la base de datos a la UI.
 *
 * - Diferencia entre LoginRequestDTO y LoginDTO:
 *   LoginRequestDTO -> enviado al servidor para autenticación.
 *   LoginDTO -> recibido desde el servidor con los datos del usuario.
 *
 * - Beneficios de usar `val`:
 *   - Inmutabilidad: los objetos no pueden ser modificados
 *     accidentalmente después de crearse.
 *   - Facilita la consistencia en la UI y el ViewModel.
 *
 * - Arquitectura típica:
 *
 *   UI / ViewModel
 *      └─ LoginRequestDTO -> API
 *   API / Data Layer
 *      └─ LoginDTO -> ViewModel / UI
 */