package com.example.multimediaapp.model

/**
 * Data class que representa la información necesaria para registrar un usuario.
 *
 * Usada al enviar datos desde la UI hacia la API para crear un nuevo usuario.
 * Todos los campos son inmutables (`val`) para garantizar consistencia.
 */
data class RegisterRequestDTO(

    // Correo electrónico del usuario
    val email: String,

    // Nombre real del usuario
    val name: String,

    // Contraseña del usuario
    val pass: String,

    // País de residencia
    val country: String,

    // Apellido del usuario
    val lastName: String
)

/**
 * Data class que representa la información completa de un usuario.
 *
 * Usada por la UI y ViewModel para mostrar información de perfil o
 * gestionar sesiones de usuario.
 * Todos los campos son inmutables (`val`) para seguridad y consistencia.
 */
data class UsersInfoDTO(
    // Identificador único de usuario, usado en listas y rutas
    val id: String,

    // Correo electrónico del usuario, usado también para login
    val email: String,

    // Contraseña (en producción debe almacenarse de forma segura, nunca en texto plano)
    val passwd: String,

    // Nombre real del usuario
    val name: String,

    // Apellido del usuario
    val lastName: String,

    // País de residencia del usuario
    val country: String
)

/**
 * APUNTES:
 *
 * - Todos los campos son `val` para inmutabilidad, seguridad y consistencia.
 * - `id` sirve para identificar usuarios en listas, repositorios o rutas de API.
 * - `email` es clave para login o contacto.
 * - `user` es el nombre que se mostrará en la UI.
 * - `passwd` no debe guardarse en texto plano en producción, siempre usar hashing.
 */