package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.RegisterFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterVM(application: Application) : AndroidViewModel(application) {

    private val repo = UsersInfoRepo(application.applicationContext)

    private val _uiState = MutableStateFlow(RegisterFormUiState())
    val uiState: StateFlow<RegisterFormUiState> = _uiState

    // Callbacks de los TextFields
    fun onUserChange(newUser: String) {
        _uiState.value = _uiState.value.copy(user = newUser, errorMessage = null)
    }

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, errorMessage = null)
    }

    fun onPassChange(newPass: String) {
        _uiState.value = _uiState.value.copy(pass = newPass, errorMessage = null)
    }

    // Validación de campos + registro
    fun validateFields(onSuccess: () -> Unit) {
        val state = _uiState.value

        when {
            state.user.isBlank() -> _uiState.value =
                state.copy(errorMessage = "El usuario no puede estar vacío")
            state.email.isBlank() || !state.email.contains("@") -> _uiState.value =
                state.copy(errorMessage = "Email inválido")
            state.pass.length < 4 -> _uiState.value =
                state.copy(errorMessage = "La contraseña debe tener al menos 4 caracteres")
            else -> {
                _uiState.value = state.copy(errorMessage = null)
                // Si todo está correcto, llamamos al registro
                registerUser(state.user, state.email, state.pass, onSuccess)
            }
        }
    }

    private fun registerUser(
        user: String,
        email: String,
        pass: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            val result = repo.register(user, email, pass)
            result.fold(
                onSuccess = {
                    _uiState.value = _uiState.value.copy(errorMessage = null)
                    onSuccess() // Navegar a otra pantalla o mostrar diálogo
                },
                onFailure = { ex ->
                    _uiState.value = _uiState.value.copy(errorMessage = ex.message)
                }
            )
        }
    }
}
