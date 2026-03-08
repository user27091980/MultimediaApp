package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.RegisterFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterVM : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterFormUiState())
    val uiState: StateFlow<RegisterFormUiState> = _uiState.asStateFlow()

    fun onUserChange(newUser: String) {
        _uiState.value = _uiState.value.copy(user = newUser, errorMessage = null)
    }

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, errorMessage = null)
    }

    fun onPassChange(newPass: String) {
        _uiState.value = _uiState.value.copy(pass = newPass, errorMessage = null)
    }

    fun validateFields(): Boolean {
        val state = _uiState.value

        if (state.user.isBlank()) {
            _uiState.value = state.copy(errorMessage = "El usuario no puede estar vacío")
            return false
        }
        if (state.email.isBlank() || !state.email.contains("@")) {
            _uiState.value = state.copy(errorMessage = "Email inválido")
            return false
        }
        if (state.pass.length < 4) {
            _uiState.value = state.copy(errorMessage = "La contraseña debe tener al menos 4 caracteres")
            return false
        }
        _uiState.value = state.copy(errorMessage = null)
        return true
    }
}
