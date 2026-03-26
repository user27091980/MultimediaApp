package com.example.multimediaapp.viewmodel.uistate

import com.example.multimediaapp.model.BandDTO

/**
 * BandListUiState:
 *
 * Representa el estado de la lista de bandas en la UI.
 * - Se utiliza en un ViewModel para exponer datos de manera reactiva.
 *
 * Propiedades:
 * @property bands Lista de objetos BandDTO que se mostrarán en la pantalla.
 * @property isLoading Indica si los datos se están cargando. Útil para mostrar un ProgressBar.
 * @property error Mensaje de error en caso de fallo al obtener los datos. Null si no hay error.
 *
 * Uso típico:
 * val state by viewModel.uiState.collectAsState()
 * if (state.isLoading) { ... }
 * if (state.error != null) { ... }
 * LazyColumn { items(state.bands) { ... } }
 */
data class BandListUiState(
    val bands: List<BandDTO> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

