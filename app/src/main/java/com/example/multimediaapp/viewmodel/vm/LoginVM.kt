package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.LoginRepo
import com.example.multimediaapp.viewmodel.uistate.LoginUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginVM(private val repo: LoginRepo) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()
    val events = _events.asSharedFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun validateFieldsLogin(): Boolean {
        val state = _uiState.value
        if (state.email.isBlank() || !state.email.contains("@")) {
            _uiState.update { it.copy(errorMessage = "Email inválido") }
            return false
        }
        if (state.password.length < 4) {
            _uiState.update { it.copy(errorMessage = "Contraseña mínima 4 caracteres") }
            return false
        }
        _uiState.update { it.copy(errorMessage = null) }
        return true
    }

    fun login() {
        if (!_uiState.value.isLoginButtonEnabled) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            // Validación local antes de llamar al repo
            if (!validateFieldsLogin()) {
                _uiState.update { it.copy(isLoading = false) }
                return@launch
            }

            // Llamada al repositorio
            val result = repo.login(_uiState.value.email, _uiState.value.password)

            result.fold(
                onSuccess = { user ->
                    _uiState.update { it.copy(isLoading = false, user = user.email) }
                    _events.emit(LoginEvent.NavigateToHome)
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = error.message ?: "Error desconocido")
                    }
                }
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}

// Factory para inyectar LoginRepo
class LoginVMFactory(private val repo: LoginRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginVM::class.java)) {
            return LoginVM(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// Eventos de un solo uso
sealed class LoginEvent {
    object NavigateToHome : LoginEvent()
    data class ShowError(val message: String) : LoginEvent()
    object HideError : LoginEvent()
}

/**
 * LoginVM (ViewModel de Login):
 *
 * Este ViewModel gestiona toda la lógica de la pantalla de inicio de sesión
 * dentro del patrón MVVM.
 *
 * RESPONSABILIDADES:
 *
 * - Gestionar el estado del formulario (email, password, loading, errores).
 * - Validar los datos del usuario.
 * - Simular el proceso de login.
 * - Emitir eventos de navegación o errores.
 * - Mantener la UI desacoplada de la lógica de negocio.
 *
 * ARQUITECTURA:
 *
 * UI (Compose)
 *     ↓
 * LoginVM
 *     ↓
 * Repository / Lógica interna (simulada en este caso)
 *
 * TIPOS DE ESTADO:
 *
 * 1. StateFlow (estado persistente)
 *    → _uiState
 *    → Contiene los datos visibles en la UI.
 *
 * 2. SharedFlow (eventos de un solo uso)
 *    → _events
 *    → Navegación, errores, acciones puntuales.
 *
 * FLUJO GENERAL:
 *
 * 1. El usuario escribe en los campos:
 *      → onEmailChange()
 *      → onPasswordChange()
 *
 * 2. El ViewModel actualiza el estado:
 *      → _uiState.update { ... }
 *
 * 3. La UI se recompone automáticamente.
 *
 * 4. Al pulsar "Login":
 *      → login()
 *
 * 5. El ViewModel:
 *      - Valida datos
 *      - Simula llamada (delay)
 *      - Actualiza estado
 *      - Emite evento (navegación o error)
 *
 * VARIABLES:
 *
 * _uiState:
 * - Estado interno mutable.
 * - Tipo: MutableStateFlow<LoginUiState>
 *
 * uiState:
 * - Estado público (solo lectura).
 * - Observado por la UI.
 *
 * _events:
 * - Canal de eventos de un solo uso.
 * - Tipo: MutableSharedFlow<LoginEvent>
 *
 * events:
 * - Versión pública del flujo de eventos.
 *
 * viewModelScope:
 * - Scope de corrutinas ligado al ciclo de vida del ViewModel.
 * - Evita fugas de memoria.
 *
 * MÉTODOS:
 *
 * onEmailChange(email):
 * - Actualiza el email.
 * - Limpia errores.
 *
 * onPasswordChange(password):
 * - Actualiza la contraseña.
 * - Limpia errores.
 *
 * togglePasswordVisibility():
 * - Alterna mostrar/ocultar contraseña.
 *
 * login():
 * - Simula el proceso de autenticación.
 * - Verifica si el botón está habilitado.
 * - Cambia estado a loading.
 * - Simula delay (llamada a API).
 * - Valida credenciales.
 * - Emite evento de navegación o error.
 *
 * validateFieldsLogin():
 * - Valida email y password antes del login.
 * - Email debe contener '@'.
 * - Password mínimo 4 caracteres.
 *
 * clearError():
 * - Limpia el mensaje de error en la UI.
 *
 * LOGIN SIMULADO:
 *
 * Credenciales válidas:
 * - email: admin@test.com
 * - password: 1234
 *
 * Si coinciden:
 * - Se considera login exitoso.
 * - Se emite NavigateToHome.
 *
 * Si no coinciden:
 * - Se muestra error: "Credenciales incorrectas".
 *
 * BENEFICIOS:
 *
 * - Separación de lógica y UI.
 * - Estado reactivo con StateFlow.
 * - Manejo de eventos con SharedFlow.
 * - Código limpio y mantenible.
 *
 * NOTA IMPORTANTE:
 *
 * - _uiState se usa para datos persistentes.
 * - _events se usa para acciones de un solo uso.
 */
