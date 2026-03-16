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
 * RegisterVM:
 * ViewModel encargado de la pantalla de registro.
 *
 * Gestiona el estado reactivo del formulario, validación de campos
 * y la comunicación con UsersInfoRepo para registrar al usuario.
 *
 * Usamos AndroidViewModel para poder obtener el contexto de la app
 * necesario para SessionManager y Repositorios.
 */
class RegisterVM(application: Application) : AndroidViewModel(application) {

    // Repositorio centralizado para operaciones de usuario
    private val repo = UsersInfoRepo(application.applicationContext)

    // Estado interno del formulario
    private val _uiState = MutableStateFlow(
        RegisterFormUiState(
            errorMessage = null,
            lastName = "",
            user = "",
            email = "",
            pass = "",
            name = "",
            surname = "",
            country = "",
            isLoading = false, // CORRECCIÓN: iniciar en false, no en true
        )
    )

    // Exposición solo lectura del estado
    val uiState: StateFlow<RegisterFormUiState> = _uiState.asStateFlow()

    // --- ACTUALIZACIÓN DE CAMPOS ---
    fun onUserChange(newUser: String) =
        _uiState.update { it.copy(user = newUser, errorMessage = null) }

    fun onEmailChange(newEmail: String) =
        _uiState.update { it.copy(email = newEmail, errorMessage = null) }

    fun onPassChange(newPass: String) =
        _uiState.update { it.copy(pass = newPass, errorMessage = null) }

    fun onNameChange(newName: String) =
        _uiState.update { it.copy(name = newName, errorMessage = null) }

    fun onLastNameChange(newLastname: String) =
        _uiState.update { it.copy(lastName = newLastname, errorMessage = null) }

    fun onCountryChange(newCountry: String) =
        _uiState.update { it.copy(country = newCountry, errorMessage = null) }

    // --- VALIDACIÓN DE CAMPOS ---
    /**
     * Valida todos los campos del formulario antes de enviar a la API.
     * Si hay error, se actualiza errorMessage.
     * Si todo está correcto, ejecuta registerUser() y llama a onSuccess.
     */
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
            // Llamada a la función que hace la petición de registro
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

    // --- REGISTRO EN LA API ---
    /**
     * Llama a UsersInfoRepo.register() para enviar los datos al servidor.
     * Actualiza isLoading mientras se realiza la operación y errorMessage si falla.
     */
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