package com.example.multimediaapp.viewmodel.uistate

import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.model.MainDTO

/**
 * Estado de la pantalla principal.
 * Contiene toda la información necesaria para renderizar la UI.
 */
data class MainUiState(

    val mainBands: List<MainDTO> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

