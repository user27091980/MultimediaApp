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

/*====APUNTES====
id: sirve para identificar la banda de manera única, útil para rutas o búsquedas.

bandName: nombre que se mostrará en la UI.

imageBand → ruta o URL de la imagen que se renderizará en la lista de bandas.

Esto permite que tu MainScreen y CardList funcionen de forma consistente y segura.
 */