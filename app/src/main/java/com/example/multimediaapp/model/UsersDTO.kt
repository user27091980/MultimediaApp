package com.example.multimediaapp.model

/**
 * Data class que representa un usuario en la aplicación.
 *
 * Todos los campos son `val` para que los objetos sean inmutables,
 * evitando cambios accidentales después de la creación.
 */
//usar val para evitar mutabilidad
data class UsersDTO(
    val id: String,
    val email: String,
    val user: String,
    val pass: String
)

