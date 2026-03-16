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
    val user: String,
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