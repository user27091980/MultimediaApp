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
    val user: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    /**
     * Computed property para habilitar/deshabilitar el botón de login.
     *
     * Retorna true solo si:
     * - Email y password no están vacíos
     * - No hay un proceso de login en curso
     */
    val isLoginButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && !isLoading
}
/*
 * LoginUiState:
 *
 * CLASE DE ESTADO (UI STATE)
 *
 * Representa todo el estado de la pantalla de Login.
 * Se utiliza dentro del patrón MVVM para mantener la UI reactiva
 * y separar la lógica del ViewModel de la interfaz.
 *
 * OBJETIVO:
 *
 * - Gestionar los datos introducidos por el usuario.
 * - Controlar la visibilidad de la contraseña.
 * - Manejar estados de carga y errores.
 * - Determinar cuándo habilitar el botón de login.
 *
 * PROPIEDADES:
 *
 * user:
 * - Nombre del usuario.
 * - Se puede usar tras un login exitoso.
 *
 * email:
 * - Correo electrónico introducido en el formulario.
 * - Puede inicializarse con un valor por defecto (ejemplo: testing).
 *
 * password:
 * - Contraseña introducida por el usuario.
 *
 * passwordVisible:
 * - Controla si la contraseña se muestra o está oculta.
 * - true → se muestra en texto plano.
 * - false → se oculta (modo seguro).
 *
 * isLoading:
 * - Indica si se está realizando el login.
 * - true → se puede mostrar un ProgressBar y deshabilitar botones.
 *
 * errorMessage:
 * - Mensaje de error a mostrar en la UI.
 * - null → no hay error.
 *
 * PROPIEDADES COMPUTADAS:
 *
 * isLoginButtonEnabled:
 * - Determina si el botón de login está activo.
 *
 * LÓGICA:
 *
 * - email no vacío
 * - password no vacío
 * - no estar en estado de carga
 *
 * IMPLEMENTACIÓN:
 *
 * val isLoginButtonEnabled: Boolean
 *     get() = email.isNotBlank() && password.isNotBlank() && !isLoading
 *
 * FLUJO DE USO:
 *
 * 1. El usuario escribe en los campos:
 *      email / password
 *
 * 2. El ViewModel actualiza el estado:
 *      uiState = uiState.copy(...)
 *
 * 3. La UI observa cambios:
 *      val state by loginVM.uiState.collectAsState()
 *
 * 4. La UI reacciona:
 *
 *    - Habilita/deshabilita botón:
 *        Button(enabled = state.isLoginButtonEnabled)
 *
 *    - Muestra loading:
 *        if (state.isLoading)
 *
 *    - Muestra error:
 *        state.errorMessage?.let { Text(it) }
 *
 * BENEFICIOS:
 *
 * - Estado centralizado.
 * - UI completamente reactiva.
 * - Separación de responsabilidades.
 * - Fácil mantenimiento y testing.
 *
 * INMUTABILIDAD:
 *
 * - Es un data class.
 * - Sus propiedades son inmutables (val).
 * - Se actualiza mediante:
 *      copy()
 *
 * NOTAS IMPORTANTES:
 *
 * - passwordVisible se usa junto con:
 *      VisualTransformation
 *   para mostrar/ocultar la contraseña.
 *
 * - Los valores por defecto (email y password) pueden usarse
 *   para pruebas o desarrollo.
 */