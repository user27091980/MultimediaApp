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
 * @author: Andrés
 */
class LoginVM : ViewModel() {
    // ESTADO DE LA UI
    // MutableStateFlow que contiene el estado de la pantalla de login
    private val _uiState = MutableStateFlow(LoginUiState())
    // Exponemos el estado como inmutable para que la UI lo observe
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Eventos de un solo uso (navegación)
    // MutableSharedFlow para eventos de un solo uso (como navegación o errores)
    private val _events = MutableSharedFlow<LoginEvent>()
    // Exponemos el flujo inmutable de eventos
    val events = _events.asSharedFlow()


    // ACTUALIZACIÓN DE CAMPOS
    // Actualiza el campo email y limpia errores previos
    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(email = email, errorMessage = null)
        }
    }
    // Actualiza el campo password y limpia errores previos
    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(password = password, errorMessage = null)
        }
    }
    //Alterna la visibilidad de la contraseña
    fun togglePasswordVisibility() {
        _uiState.update {
            it.copy(passwordVisible = !it.passwordVisible)
        }
    }

    // LOGIN
    /**
     * Simula un login:
     * - Verifica si el botón está habilitado.
     * - Cambia el estado a loading.
     * - Simula delay de validación.
     * - Actualiza estado y emite eventos de navegación o error.
     */

    fun login() {

        if (!_uiState.value.isLoginButtonEnabled) return

        viewModelScope.launch {
            // Indicamos que la UI está cargando
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }
            // Simulación de delay (llamada a API)
            delay(2000)
            // Validación simulada de credenciales
            if (_uiState.value.email == "admin@test.com" &&
                _uiState.value.password == "1234"
            ) {
                // Login exitoso
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        user = "Administrador"
                    )
                }

                //  Emitimos el evento de navegación
                _events.emit(LoginEvent.NavigateToHome)

            } else {
                // Credenciales incorrectas
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        // Emitimos evento para mostrar mensaje de error
                        errorMessage = "Credenciales incorrectas"
                    )
                }
            }
        }
    }
    //Limpia cualquier mensaje de error visible en la UI
    fun clearError() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
    /**
     * Valida los campos antes de intentar login.
     *
     * @return true si los campos son válidos, false si hay errores
     */
    fun validateFieldsLogin(): Boolean {
        val state = _uiState.value

        if (state.email.isBlank() || !state.email.contains("@")) {
            _uiState.value = state.copy(errorMessage = "Email inválido")
            return false
        }
        if (state.password.length < 4) {
            _uiState.value =
                state.copy(errorMessage = "La contraseña debe tener al menos 4 caracteres")
            return false
        }
        _uiState.value = state.copy(errorMessage = null)
        return true
    }
}
/**
 * Eventos de un solo uso para la pantalla de Login.
 *
 * Sealed class permite un manejo seguro y exhaustivo en la UI.
 */
sealed class LoginEvent {

    /** Navegar a la pantalla principal (Home) tras login exitoso */
    object NavigateToHome : LoginEvent()

    /** Mostrar mensaje de error genérico */
    data class ShowError(val message: String) : LoginEvent()

    /** Ocultar mensaje de error */
    object HideError : LoginEvent()
}


