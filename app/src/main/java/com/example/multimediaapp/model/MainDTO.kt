package com.example.multimediaapp.model

/**
 * Data class que representa la información principal de una banda
 * para mostrar en la pantalla MainScreen.
 *
 * Cada objeto corresponde a una banda con su nombre y una imagen asociada.
 */
data class MainDTO(
    // Identificador único de la banda
    val id: String,
    val bandName: String,
    // imagen de la banda
    val imageBand: String

)