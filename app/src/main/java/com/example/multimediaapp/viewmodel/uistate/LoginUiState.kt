package com.example.multimediaapp.viewmodel.uistate

/**
 * Representa el estado completo de la pantalla de Login.
 *
 * - Contiene toda la información que la UI necesita para renderizar la pantalla.
 * - Permite al ViewModel manejar cambios de forma reactiva.
 * - Incluye campos para email, password, visibilidad de password, estado de carga y errores.
 */
data class LoginUiState(
    /** Nombre del usuario, útil para mostrar en pantalla tras login exitoso */
    val user: String="",
    /** Correo electrónico ingresado por el usuario */
    val email: String = "",
    /** Contraseña ingresada por el usuario */
    val password: String = "",
    /** Indica si la contraseña es visible en la UI (toggle show/hide) */
    val passwordVisible: Boolean = false,
    /** Indica si hay un proceso de login en curso */
    val isLoading: Boolean = false,
    /** Mensaje de error que se muestra en la UI en caso de fallo */
    val errorMessage: String? = null
) {
    /**
     * Computed property para habilitar o deshabilitar el botón de login.
     *
     * - El botón solo está habilitado si:
     *   1️⃣ Email y password no están vacíos
     *   2️⃣ No hay un proceso de login en curso (`isLoading == false`)
     */
    val isLoginButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && !isLoading
}