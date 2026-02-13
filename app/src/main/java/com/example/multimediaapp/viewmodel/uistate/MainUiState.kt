package com.example.multimediaapp.viewmodel.uistate

/**
 * Estado de la pantalla principal.
 * Contiene toda la información necesaria para renderizar la UI.
 */
data class MainUiState(

    // Lista de bandas que se mostrarán en la pantalla
    val bands: List<BandUiState> = emptyList(),

    // Indica si los datos están cargando
    val isLoading: Boolean = false,

    // Mensaje de error en caso de fallo
    val error: String? = null
)
