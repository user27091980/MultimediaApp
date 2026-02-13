package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.viewmodel.uistate.LoginUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay


/**
 * ViewModel encargado de gestionar la pantalla de login.
 *
 * Funciona dentro de la arquitectura MVVM:
 * - No contiene lógica de UI.
 * - Expone un estado observable para la UI (email, password, loading, errores).
 * - Permite eventos de un solo uso, como la navegación tras login.
 *
 * Maneja:
 * - Actualización de email y contraseña.
 * - Mostrar/ocultar contraseña.
 * - Validación simulada de credenciales.
 * - Manejo de errores en el login.
 *
 * Autor: Andrés
 */
class LoginVM : ViewModel() {
    /** Estado interno mutable */
    private val _uiState = MutableStateFlow(LoginUiState())

    /** Estado público inmutable para la UI */
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // -------------------------------------------------
    // ACTUALIZACIÓN DE CAMPOS
    // -------------------------------------------------

    /**
     * Actualiza el email en el estado.
     * Limpia cualquier mensaje de error previo.
     * @param email nuevo valor del email
     */
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            errorMessage = null
        )
    }

    /**
     * Actualiza la contraseña en el estado.
     * Limpia cualquier mensaje de error previo.
     * @param password nuevo valor de la contraseña
     */
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            errorMessage = null
        )
    }

    /**
     * Alterna la visibilidad de la contraseña.
     * Permite mostrar u ocultar el contenido de la contraseña.
     */
    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            passwordVisible = !_uiState.value.passwordVisible
        )
    }

    // -------------------------------------------------
    // LÓGICA DE LOGIN
    // -------------------------------------------------

    /**
     * Simula el proceso de login.
     *
     * 1️⃣ Verifica si el botón de login está habilitado.
     * 2️⃣ Muestra indicador de carga.
     * 3️⃣ Espera simulando llamada a servidor (2 segundos).
     * 4️⃣ Valida credenciales:
     *     - Correctas → actualiza usuario logueado.
     *     - Incorrectas → muestra mensaje de error.
     */
    fun login() {
        // Evita login si los campos no son válidos
        if (!_uiState.value.isLoginButtonEnabled) return

        viewModelScope.launch {
            // Activamos loading y limpiamos errores
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            // Simulación llamada a servidor
            delay(2000)
            // Validación de credenciales simulada
            if (_uiState.value.email == "admin@test.com" &&
                _uiState.value.password == "1234"
            ) {
                // Login correcto
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    user = "Administrador"
                )
            } else {
                // Login incorrecto
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Credenciales incorrectas"
                )
            }
        }
    }
// -------------------------------------------------
    // LIMPIEZA DE ESTADO
    // -------------------------------------------------

    /**
     * Limpia el mensaje de error del estado.
     * Útil después de mostrar un Snackbar o un diálogo.
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}
