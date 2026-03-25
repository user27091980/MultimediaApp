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

/*
 * BandListUiState:
 *
 * CLASE DE ESTADO (UI STATE)
 *
 * Representa el estado completo de la pantalla donde se muestra
 * la lista de bandas.
 *
 * Este patrón se utiliza dentro del enfoque MVVM para:
 *
 * - Centralizar el estado de la UI.
 * - Hacer la interfaz reactiva.
 * - Evitar lógica en la capa de presentación.
 *
 * PROPIEDADES:
 *
 * bands:
 * - Lista de objetos BandDTO.
 * - Contiene los datos que se muestran en pantalla.
 * - Se usa normalmente en un LazyColumn o lista similar.
 *
 * isLoading:
 * - Booleano que indica si se están cargando los datos.
 * - Se usa para mostrar indicadores de carga (ProgressBar).
 *
 * error:
 * - Contiene un mensaje de error si algo falla.
 * - Si es null, significa que no hay errores.
 *
 * FLUJO DE USO:
 *
 * 1. El ViewModel obtiene los datos (API, base de datos, etc.).
 *
 * 2. Actualiza el estado:
 *      uiState = uiState.copy(...)
 *
 * 3. La UI observa el estado:
 *      val state by viewModel.uiState.collectAsState()
 *
 * 4. La UI reacciona automáticamente:
 *
 *    if (state.isLoading)
 *        → muestra loading
 *
 *    if (state.error != null)
 *        → muestra error
 *
 *    if (state.bands.isNotEmpty())
 *        → muestra lista
 *
 * BENEFICIOS:
 *
 * - Estado único y centralizado.
 * - Fácil mantenimiento y escalabilidad.
 * - Separación clara entre lógica y UI.
 * - Compatible con Compose y su sistema reactivo.
 *
 * INMUTABILIDAD:
 *
 * - Es un data class.
 * - Sus propiedades son inmutables (val).
 * - Para actualizar, se usa:
 *      copy()
 *
 * EJEMPLO DE USO:
 *
 * val state = BandListUiState(
 *     bands = listOf(...),
 *     isLoading = false,
 *     error = null
 * )
 *
 * Luego en la UI:
 *
 * when {
 *     state.isLoading -> ...
 *     state.error != null -> ...
 *     else -> mostrar lista
 * }
 */