package com.example.multimediaapp.viewmodel.uistate

/**
 * LoginUiState:
 *
 * Representa el estado completo de la pantalla de Login.
 * Se usa en el ViewModel para exponer información de manera reactiva.
 *
 * Propiedades:
 * @property user Nombre del usuario (útil después del login exitoso).
 * @property email Correo electrónico ingresado en el formulario.
 * @property password Contraseña ingresada en el formulario.
 * @property passwordVisible Indica si la contraseña se muestra en texto plano (toggle show/hide).
 * @property isLoading Indica si hay un proceso de login en curso (para mostrar progress bar, deshabilitar botones, etc.).
 * @property errorMessage Mensaje de error que se muestra en la UI. Null si no hay errores.
 *
 * Computed properties:
 * @property isLoginButtonEnabled Evalúa si el botón de login debe estar activo:
 * - Debe haber email y password no vacíos.
 * - No debe estar en proceso de login.
 *
 * Ejemplo de uso en Compose:
 * val state by loginVM.uiState.collectAsState()
 * Button(enabled = state.isLoginButtonEnabled) { login() }
 * if (state.isLoading) { CircularProgressIndicator() }
 * state.errorMessage?.let { Text(it, color = Color.Red) }
 */
data class LoginUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val rememberMe: Boolean = false
) {
    /**
     * Computed property para habilitar/deshabilitar el botón de login.
     *
     * Retorna true solo si:
     * - Email y password no están vacíos
     * - No hay un proceso de login en curso
     */
    val isLoginButtonEnabled: Boolean
        get() = name.isNotBlank() && password.isNotBlank() && !isLoading
}