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
/*
 * LoginRegUiState:
 *
 * CLASE DE ESTADO (UI STATE)
 *
 * Representa el estado de la pantalla de Login / Registro.
 * Forma parte del patrón MVVM y permite gestionar la UI de forma reactiva,
 * separando la lógica del ViewModel de la interfaz de usuario.
 *
 * OBJETIVO:
 *
 * - Controlar la habilitación de botones.
 * - Mostrar estados de carga.
 * - Mostrar mensajes de error.
 * - Evitar lógica directamente en la UI.
 *
 * PROPIEDADES:
 *
 * isLoginEnabled:
 * - Indica si el botón de login está activo.
 * - false → el botón está deshabilitado.
 * - Se usa normalmente en validaciones de campos.
 *
 * isRegisterEnabled:
 * - Indica si el botón de registro está activo.
 * - Controla si el usuario puede iniciar el proceso de registro.
 *
 * isLoading:
 * - Indica si se está realizando una operación (login o registro).
 * - true → se suele mostrar un ProgressBar.
 * - Evita múltiples peticiones simultáneas.
 *
 * errorMessage:
 * - Contiene un mensaje de error para mostrar en la UI.
 * - null → no hay error.
 * - String → se muestra en rojo u otro estilo de error.
 *
 * FLUJO DE USO:
 *
 * 1. El ViewModel valida datos o ejecuta una petición:
 *      login()
 *      register()
 *
 * 2. Actualiza el estado:
 *      uiState = uiState.copy(...)
 *
 * 3. La UI observa el estado:
 *      val state by viewModel.uiState.collectAsState()
 *
 * 4. La UI reacciona automáticamente:
 *
 *    Button(enabled = state.isLoginEnabled)
 *
 *    if (state.isLoading)
 *        → muestra loading
 *
 *    state.errorMessage?.let {
 *        → muestra error
 *    }
 *
 * BENEFICIOS:
 *
 * - Estado centralizado y predecible.
 * - UI completamente reactiva.
 * - Separación clara entre lógica y presentación.
 * - Facilita testing y mantenimiento.
 *
 * INMUTABILIDAD:
 *
 * - Es un data class.
 * - Sus propiedades son inmutables (val).
 * - Para actualizar se usa:
 *      copy()
 *
 * EJEMPLO DE USO:
 *
 * val state = LoginRegUiState(
 *     isLoginEnabled = true,
 *     isRegisterEnabled = false,
 *     isLoading = true,
 *     errorMessage = "Error de conexión"
 * )
 *
 * UI:
 *
 * if (state.isLoading) {
 *     CircularProgressIndicator()
 * }
 *
 * if (state.errorMessage != null) {
 *     Text(state.errorMessage)
 * }
 */