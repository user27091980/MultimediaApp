package com.example.multimediaapp.viewmodel.uistate

import com.example.multimediaapp.model.MainDTO

/**
 * MainUiState:
 *
 * Representa el estado completo de la pantalla principal (MainScreen) de la app.
 * Contiene toda la información que la UI necesita para renderizar la lista de bandas
 * y mostrar estados de carga o errores.
 *
 * Propiedades:
 * @property mainBands Lista de objetos MainDTO que representan las bandas a mostrar en la UI.
 * @property isLoading Indica si se está realizando la carga inicial de datos.
 * @property isRefreshing Indica si se está realizando un gesto de "pull-to-refresh".
 * @property error Mensaje de error a mostrar en la UI en caso de fallo; null si no hay error.
 *
 * Uso típico:
 * val state by mainVM.uiState.collectAsState()
 * LazyColumn { items(state.mainBands) { BandCard(it) } }
 * if (state.isLoading) { CircularProgressIndicator() }
 * state.error?.let { Text(it, color = Color.Red) }
 */
data class MainUiState(
    val mainBands: List<MainDTO> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false, // Útil para el gesto swipe-to-refresh
    val error: String? = null
)


