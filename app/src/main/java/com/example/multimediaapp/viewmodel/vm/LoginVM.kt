package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.viewmodel.events.LoginEvent
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
 * Autor: Andrés
 */
class LoginVM : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Eventos de un solo uso (navegación)
    private val _events = MutableSharedFlow<LoginEvent>()
    val events = _events.asSharedFlow()

    // -------------------------------------------------
    // ACTUALIZACIÓN DE CAMPOS
    // -------------------------------------------------

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(email = email, errorMessage = null)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(password = password, errorMessage = null)
        }
    }

    fun togglePasswordVisibility() {
        _uiState.update {
            it.copy(passwordVisible = !it.passwordVisible)
        }
    }

    // -------------------------------------------------
    // LOGIN
    // -------------------------------------------------

    fun login() {

        if (!_uiState.value.isLoginButtonEnabled) return

        viewModelScope.launch {

            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }

            delay(2000)

            if (_uiState.value.email == "admin@test.com" &&
                _uiState.value.password == "1234"
            ) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        user = "Administrador"
                    )
                }

                // 🔥 Emitimos evento de navegación
                _events.emit(LoginEvent.NavigateToHome)

            } else {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Credenciales incorrectas"
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}
