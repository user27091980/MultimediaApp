package com.example.multimediaapp.viewmodel.uistate

/**
 * LoginRegUiState:
 *
 * Representa el estado de la pantalla de Login / Registro.
 * Se utiliza en un ViewModel para exponer de manera reactiva
 * la información que la UI debe mostrar.
 *
 * Propiedades:
 * @property isLoginEnabled Indica si el botón de login está habilitado.
 * @property isRegisterEnabled Indica si el botón de registro está habilitado.
 * @property isLoading Indica si se está realizando una operación de red
 * (por ejemplo, login o registro).
 * @property errorMessage Mensaje de error a mostrar en la UI. Null si no hay errores.
 *
 * Ejemplo de uso:
 * val state by viewModel.uiState.collectAsState()
 * Button(enabled = state.isLoginEnabled) { ... }
 * if (state.isLoading) { CircularProgressIndicator() }
 * state.errorMessage?.let { Text(it, color = Color.Red) }
 */
data class LoginRegUiState(
    val isLoginEnabled: Boolean = true,
    val isRegisterEnabled: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)