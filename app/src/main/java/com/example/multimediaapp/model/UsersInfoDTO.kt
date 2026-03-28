package com.example.multimediaapp.model

import com.google.gson.annotations.SerializedName

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
    val passwd: String,

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
    val id: String,
    val name: String,
    val email: String,
    val lastName: String = "",
    val country: String = "",
    // Al poner = "", evitamos el error "parameter specified as non-null is null"
    val passwd: String = ""
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