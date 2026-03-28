package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.IUserInfoRepo
import com.example.multimediaapp.data.repository.UserInfoRepo
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.viewmodel.uistate.RegisterFormUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel de la pantalla de registro de usuario.
 *
 * Funciones principales:
 * - Gestionar el estado reactivo del formulario ([RegisterFormUiState]).
 * - Validar los campos de entrada antes de registrar al usuario.
 * - Comunicar con el repositorio ([UserInfoRepo]) para realizar el registro.
 * - Notificar a la UI sobre errores o estado de carga.
 *
 * Se extiende de [AndroidViewModel] para disponer del contexto de la aplicación,
 * necesario para repositorios o gestores de sesión.
 *
 * Patrón MVVM:
 * - La UI observa [uiState] y se actualiza automáticamente ante cambios.
 * - La lógica de negocio y validación queda encapsulada en el ViewModel.
 *
 * @property application Contexto de la aplicación.
 */
sealed class RegisterEvent {
    object NavigateToLogin : RegisterEvent()
    data class ShowError(val message: String) : RegisterEvent()
}

class RegisterVM(private val repo: IUserInfoRepo) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterFormUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<RegisterEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    // --- FUNCIONES DE ACTUALIZACIÓN (Deben estar todas para que la UI compile) ---
    fun onEmailChange(email: String) = _uiState.update { it.copy(email = email) }
    fun onPassChange(pass: String) = _uiState.update { it.copy(passwd = pass) }
    fun onNameChange(name: String) = _uiState.update { it.copy(name = name) }
    fun onLastNameChange(lastName: String) = _uiState.update { it.copy(lastName = lastName) }
    fun onCountryChange(country: String) = _uiState.update { it.copy(country = country) }

    fun validateAndRegister() {
        val state = _uiState.value
        // Validación básica: Email, Pass, Nombre y Apellido no vacíos
        if (state.email.isBlank() || state.passwd.length < 4 || state.name.isBlank() || state.lastName.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor, rellena todos los campos correctamente") }
            return
        }
        executeRegister(state)
    }

    private fun executeRegister(state: RegisterFormUiState) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            // Llamada al repo usando los nombres de parámetros de la interfaz IUserInfoRepo
            repo.register(
                email = state.email,
                name = state.name,
                passwd = state.passwd,
                country = state.country,
                lastName = state.lastName
            ).fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false) }
                    _events.send(RegisterEvent.NavigateToLogin)
                },
                onFailure = { ex ->
                    _uiState.update { it.copy(errorMessage = ex.message, isLoading = false) }
                }
            )
        }
    }
}


class RegisterVMFactory(private val repo: IUserInfoRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = RegisterVM(repo) as T
}
