package com.example.multimediaapp.model

/**
 * Data class que representa un usuario en la aplicación.
 *
 * Todos los campos son `val` para que los objetos sean inmutables,
 * evitando cambios accidentales después de la creación.
 */
//usar val para evitar mutabilidad
data class LoginDTO(
    val email: String,
    val pass: String
)

/*
 * Esta clase representa el modelo de datos LoginDTO utilizado en la aplicación
 * para gestionar la información básica de autenticación del usuario.
 *
 * Contiene los datos necesarios para identificar al usuario dentro del sistema:
 * - id: identificador único del usuario
 * - email: correo electrónico utilizado para el login
 * - pass: contraseña del usuario
 *
 * Se utiliza como objeto de transferencia de datos (DTO), es decir,
 * sirve para mover información entre las distintas capas de la aplicación
 * (por ejemplo, entre la capa de datos, la lógica de negocio y la UI).
 *
 * Todos los campos están definidos como val para garantizar la inmutabilidad,
 * lo que significa que una vez creado el objeto, sus valores no pueden modificarse.
 * Esto ayuda a evitar errores y hace el código más seguro y predecible.
 */