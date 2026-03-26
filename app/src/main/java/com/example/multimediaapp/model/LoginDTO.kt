package com.example.multimediaapp.model

/**
 * Data class que representa un usuario en la aplicación.
 *
 * Todos los campos son `val` para que los objetos sean inmutables,
 * evitando cambios accidentales después de la creación.
 */
//usar val para evitar mutabilidad
// package com.example.multimediaapp.model

data class LoginRequestDTO(
    val email: String,
    val pass: String
)

data class LoginDTO(
    val id: String,
    val email: String,
    val pass: String
)

