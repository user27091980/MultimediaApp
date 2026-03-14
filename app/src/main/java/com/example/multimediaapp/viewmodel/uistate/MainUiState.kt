package com.example.multimediaapp.viewmodel.uistate

import com.example.multimediaapp.model.MainDTO

/**
 * Estado de la pantalla principal.
 * Contiene toda la información necesaria para renderizar la UI.
 */
data class MainUiState(
    val mainBands: List<MainDTO> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false, // Para el gesto de deslizar hacia abajo
    val error: String? = null
)


