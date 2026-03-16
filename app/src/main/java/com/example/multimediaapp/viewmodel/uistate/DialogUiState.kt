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
    val isVisible: Boolean = false,
    val isConfirmed: Boolean = false
)