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
/*
 * MainUiState:
 *
 * CLASE DE ESTADO (UI STATE)
 *
 * Representa todo el estado de la pantalla principal (MainScreen).
 * Se utiliza dentro del patrón MVVM para manejar la UI de forma reactiva
 * y mantener separada la lógica del ViewModel.
 *
 * OBJETIVO:
 *
 * - Gestionar la lista de bandas.
 * - Controlar estados de carga.
 * - Manejar errores.
 * - Soportar acciones como refrescar contenido.
 *
 * PROPIEDADES:
 *
 * mainBands:
 * - Lista de objetos MainDTO.
 * - Representa las bandas que se mostrarán en la pantalla.
 * - Se utiliza típicamente en listas como LazyColumn.
 *
 * isLoading:
 * - Indica si se está cargando la información por primera vez.
 * - true → se puede mostrar un indicador de carga.
 *
 * isRefreshing:
 * - Indica si se está realizando un refresco manual (pull-to-refresh).
 * - Permite diferenciar entre carga inicial y actualización manual.
 *
 * error:
 * - Mensaje de error en caso de fallo (por ejemplo, error de red).
 * - null → no hay error.
 * - String → se muestra en la UI.
 *
 * FLUJO DE USO:
 *
 * 1. El ViewModel solicita datos:
 *      loadData()
 *
 * 2. Actualiza el estado:
 *      uiState = uiState.copy(...)
 *
 * 3. La UI observa el estado:
 *      val state by mainVM.uiState.collectAsState()
 *
 * 4. La UI reacciona automáticamente:
 *
 *    - Mostrar lista:
 *        LazyColumn { items(state.mainBands) { ... } }
 *
 *    - Mostrar loading:
 *        if (state.isLoading)
 *
 *    - Mostrar refresco:
 *        if (state.isRefreshing)
 *
 *    - Mostrar error:
 *        state.error?.let { Text(it) }
 *
 * BENEFICIOS:
 *
 * - Estado centralizado y predecible.
 * - UI completamente reactiva.
 * - Separación clara de responsabilidades.
 * - Fácil escalabilidad.
 *
 * INMUTABILIDAD:
 *
 * - Es un data class.
 * - Sus propiedades son inmutables (val).
 * - Se actualiza usando:
 *      copy()
 *
 * USO EN COMPOSE:
 *
 * val state by mainVM.uiState.collectAsState()
 *
 * LazyColumn {
 *     items(state.mainBands) { band ->
 *         BandCard(band)
 *     }
 * }
 *
 * if (state.isLoading) {
 *     CircularProgressIndicator()
 * }
 *
 * state.error?.let {
 *     Text(it, color = Color.Red)
 * }
 *
 * NOTAS:
 *
 * - isRefreshing se usa normalmente con componentes como:
 *     SwipeRefresh
 *
 * - mainBands puede venir de:
 *     API, base de datos o repositorio local.
 */

