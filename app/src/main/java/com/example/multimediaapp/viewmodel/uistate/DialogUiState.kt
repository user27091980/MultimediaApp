package com.example.multimediaapp.viewmodel.uistate

/**
 * DialogUiState:
 *
 * Representa el estado de un diálogo en la UI.
 * Se usa típicamente en ViewModels para controlar la visibilidad
 * y la respuesta del usuario sin manipular directamente la UI desde el ViewModel.
 *
 * Propiedades:
 * @property isVisible Indica si el diálogo debe mostrarse o no.
 * @property isConfirmed Indica si el usuario confirmó la acción en el diálogo.
 *
 * Ejemplo de uso:
 * val state by viewModel.dialogState.collectAsState()
 * if (state.isVisible) {
 *     AlertDialog(
 *         onDismissRequest = { viewModel.hideDialog() },
 *         confirmButton = {
 *             Button(onClick = { viewModel.confirmDialog() }) { Text("OK") }
 *         }
 *     )
 * }
 */
data class DialogUiState(
    val isVisible: Boolean = true,
    val isConfirmed: Boolean = false
)
/*
 * DialogUiState:
 *
 * CLASE DE ESTADO (UI STATE)
 *
 * Representa el estado de un diálogo en la interfaz de usuario.
 * Forma parte del patrón MVVM y se utiliza para controlar la UI
 * sin que el ViewModel manipule directamente los componentes visuales.
 *
 * OBJETIVO:
 *
 * - Gestionar cuándo se muestra el diálogo.
 * - Registrar la respuesta del usuario.
 * - Mantener la lógica desacoplada de la UI.
 *
 * PROPIEDADES:
 *
 * isVisible:
 * - Indica si el diálogo debe mostrarse.
 * - true → el diálogo es visible.
 * - false → el diálogo está oculto.
 *
 * isConfirmed:
 * - Indica si el usuario ha confirmado una acción.
 * - true → usuario aceptó/confirmó.
 * - false → usuario rechazó o aún no ha respondido.
 *
 * FLUJO DE USO:
 *
 * 1. El ViewModel expone el estado:
 *      val dialogState: StateFlow<DialogUiState>
 *
 * 2. La UI lo observa:
 *      val state by viewModel.dialogState.collectAsState()
 *
 * 3. La UI reacciona:
 *
 *    if (state.isVisible)
 *        → se muestra el diálogo
 *
 * 4. Acciones del usuario:
 *
 *    - Confirmar:
 *        viewModel.confirmDialog()
 *
 *    - Cancelar:
 *        viewModel.hideDialog()
 *
 * EJEMPLO DE USO EN COMPOSE:
 *
 * if (state.isVisible) {
 *     AlertDialog(
 *         onDismissRequest = { viewModel.hideDialog() },
 *         confirmButton = {
 *             Button(onClick = { viewModel.confirmDialog() }) {
 *                 Text("OK")
 *             }
 *         }
 *     )
 * }
 *
 * BENEFICIOS:
 *
 * - Control centralizado del estado del diálogo.
 * - UI reactiva y declarativa.
 * - Separación clara entre lógica y presentación.
 * - Fácil de testear y mantener.
 *
 * INMUTABILIDAD:
 *
 * - Es un data class.
 * - Sus propiedades son inmutables (val).
 * - Se actualiza usando:
 *      copy()
 *
 * NOTA:
 *
 * - Por defecto:
 *      isVisible = true → el diálogo se muestra inicialmente.
 * - Se puede modificar según la lógica del ViewModel.
 */