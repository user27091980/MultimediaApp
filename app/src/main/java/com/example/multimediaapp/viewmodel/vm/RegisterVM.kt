package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.RegisterFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
/**
 * ViewModel para la pantalla de registro de usuarios.
 *
 * Gestiona el estado del formulario de registro y la lógica de validación/registro.
 * Usa AndroidViewModel para obtener el contexto de la aplicación.
 *
 * @author: Andrés
 */


class RegisterVM(application: Application) : AndroidViewModel(application) {

    private val repo = UsersInfoRepo(application.applicationContext)

    // Inicialización corregida (sin duplicar el constructor)
    private val _uiState = MutableStateFlow(RegisterFormUiState(
        errorMessage = "error",
        lastName = "",
        user = "",
        email = "",
        pass = "",
        name = "",
        surname = "",
        country = "",
        isLoading = true,
    ))
    val uiState: StateFlow<RegisterFormUiState> = _uiState.asStateFlow()

    // --- ACTUALIZACIÓN DE ESTADOS ---
    fun onUserChange(newUser: String) =
        _uiState.update { it.copy(user = newUser, errorMessage = null) }

    fun onEmailChange(newEmail: String) =
        _uiState.update { it.copy(email = newEmail, errorMessage = null) }

    // CORRECCIÓN: Estaba actualizando el email en lugar del pass
    fun onPassChange(newPass: String) =
        _uiState.update { it.copy(pass = newPass, errorMessage = null) }

    fun onNameChange(newName: String) =
        _uiState.update { it.copy(name = newName, errorMessage = null) }

    fun onLastNameChange(newLastname: String) =
        _uiState.update { it.copy(lastName = newLastname, errorMessage = null) }

    fun onCountryChange(newCountry: String) =
        _uiState.update { it.copy(country = newCountry, errorMessage = null) }

    fun validateFields(onSuccess: () -> Unit) {
        val state = _uiState.value
        val error = when {
            state.user.isBlank() -> "El usuario es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> "Email inválido"
            state.pass.length < 4 -> "La contraseña debe tener al menos 4 caracteres"
            state.name.isBlank() || state.lastName.isBlank() -> "Nombre y apellido son obligatorios"
            else -> null
        }

        if (error != null) {
            _uiState.update { it.copy(errorMessage = error) }
        } else {
            // CORRECCIÓN: Quitamos el TODO() y pasamos la lógica correcta
            registerUser(
                email = state.email,
                pass = state.pass,
                user = state.user,
                name = state.name,
                lastName = state.lastName,
                country = state.country,
                onSuccess = onSuccess
            )
        }
    }

    private fun registerUser(
        email: String,
        pass: String,
        user: String,
        name: String,
        lastName: String,
        country: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            // CORRECCIÓN: El orden de parámetros debe coincidir con UsersInfoRepo.register
            val result = repo.register(
                email = email,
                user = user,
                name = name,
                pass = pass,
                country = country,
                lastName = lastName
            )

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false) }
                    onSuccess()
                },
                onFailure = { ex ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = ex.message) }
                }
            )
        }
    }
}
