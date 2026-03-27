package com.example.multimediaapp.model

/**
 * Data class que representa la información principal de una banda
 * para mostrar en la pantalla principal (MainScreen) de la aplicación.
 *
 * Cada objeto corresponde a una banda con su nombre y una imagen asociada.
 * Se usa `val` para que los objetos sean inmutables y consistentes.
 */
data class MainDTO(
    // Identificador único de la banda, útil para búsquedas y actualizaciones
    val id: String,

    // Nombre de la banda
    val bandName: String,

    // URL o recurso de la imagen de la banda que se mostrará en la UI
    val imageBand: String
)

/**
 * EXPLICACIÓN:
 *
 * - ¿Por qué DTO?
 *   MainDTO es un Data Transfer Object (DTO) que permite transportar
 *   datos desde la capa de datos (API/Repositorios) hacia la UI
 *   sin exponer detalles internos de la API o la base de datos.
 *
 * - Beneficios de usar `val`:
 *   - Inmutabilidad: los objetos no pueden ser modificados accidentalmente.
 *   - Garantiza que los datos mostrados en la UI permanezcan consistentes.
 *
 * - Arquitectura típica:
 *
 *   API / Data Layer -> MainDTO -> ViewModel -> UI
 *
 * - Diferencia con MainEntity (si existe):
 *   MainEntity -> representa la estructura de la API o base de datos.
 *   MainDTO -> representa la estructura que la aplicación usa para mostrar datos.
 */