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
 * Viewmodel that manages the login screen login.
 *
 * - uses the UI state (email, password, loading, errors)
 * - for emited events of only one time (navigation)
 *
 * @author Andrés
 *
 */
class LoginVM : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())

    // Estado público inmutable
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Actualizar email
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            errorMessage = null
        )
    }

    // Actualizar password
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            errorMessage = null
        )
    }

    // Mostrar / ocultar contraseña
    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            passwordVisible = !_uiState.value.passwordVisible
        )
    }

    // Simulación de login
    fun login() {
        if (!_uiState.value.isLoginButtonEnabled) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            // Simulación llamada a servidor
            delay(2000)

            if (_uiState.value.email == "admin@test.com" &&
                _uiState.value.password == "1234"
            ) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    user = "Administrador"
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Credenciales incorrectas"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}
