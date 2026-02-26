package com.example.multimediaapp.viewmodel.uistate


/**
 * Representa el estado de la lista de bandas en la UI.
 *
 * - Contiene la lista completa de bandas que se mostrarán en pantalla.
 * - Se inicializa con una lista vacía para evitar valores nulos.
 * - Inmutable, de manera que solo el ViewModel puede actualizarlo mediante `copy()` o creando un nuevo objeto.
 */
data class BandListUiState(val bands: List<BandUiState> = ArrayList())
/**
 * Representa la información de una sola banda.
 *
 * - Cada objeto de esta clase contiene todos los datos necesarios para renderizar
 *   una banda en la UI.
 */
data class BandUiState(
    /** ID único de la banda */
    val id: String,
    /** Nombre de la banda */
    val name: String,
    /** Descripción o biografía de la banda */
    val textInfo: String,
    /** Imagen principal o cabecera de la banda */
    val headerImage: String,
    /** Lista de imágenes de álbumes de la banda */
    val albumImages: List<String>,
    /** Estilo o género musical de la banda */
    val style: String,
    /** Discográfica a la que pertenece la banda */
    val recordLabel: String,
    /** Componentes o integrantes de la banda */
    val components: String,
    /** Discografía de la banda (lista de álbumes) */
    val discography: List<String>,
    /** Imagen principal de la banda, usada en listas o detalles */
    val imageBand: String

)

