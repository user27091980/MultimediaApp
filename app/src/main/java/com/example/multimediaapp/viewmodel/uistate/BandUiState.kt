package com.example.multimediaapp.viewmodel.uistate

import com.example.multimediaapp.model.BandDTO


/**
 * Representa la información de una sola banda.
 *
 * - Cada objeto de esta clase contiene todos los datos necesarios para renderizar
 *   una banda en la UI.
 */
data class BandListUiState(
    val bands: List<BandDTO> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

