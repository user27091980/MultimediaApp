package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UsersInfoRepo
import com.example.multimediaapp.viewmodel.uistate.RegisterFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    // Repositorio que maneja la persistencia/lectura de usuarios
    private val repo = UsersInfoRepo(application.applicationContext)
    // ESTADO DE LA UI
    // MutableStateFlow que mantiene el estado actual del formulario de registro
    private val _uiState = MutableStateFlow(RegisterFormUiState())
    // Exponemos el estado como inmutable para que la UI lo observe
    val uiState: StateFlow<RegisterFormUiState> = _uiState

    // Callbacks de los TextFields
    // Actualiza el campo "usuario" y limpia errores previos
    fun onUserChange(newUser: String) {
        _uiState.value = _uiState.value.copy(user = newUser, errorMessage = null)
    }
    //Actualiza el campo "email" y limpia errores previos
    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, errorMessage = null)
    }
    //Actualiza el campo "password" y limpia errores previos
    fun onPassChange(newPass: String) {
        _uiState.value = _uiState.value.copy(pass = newPass, errorMessage = null)
    }

    // Validación de campos y registro
    /**
     * Valida los campos del formulario antes de registrar al usuario.
     *
     * @param onSuccess Callback que se ejecuta si la validación y registro son exitosos.
     */
    fun validateFields(onSuccess: () -> Unit) {
        val state = _uiState.value

        when {
            state.user.isBlank() -> _uiState.value =
                state.copy(errorMessage = "El usuario no puede estar vacío")
            state.email.isBlank() || !state.email.contains("@") -> _uiState.value =
                state.copy(errorMessage = "Email inválido")
            state.pass.length < 4 -> _uiState.value =
                state.copy(errorMessage = "La contraseña debe tener al menos 4 caracteres")
            // Si todo está correcto, llamamos a la función que registra al usuario
            else -> {
                _uiState.value = state.copy(errorMessage = null)

                registerUser(state.user, state.email, state.pass, onSuccess)
            }
        }
    }
    /**
     * Llama al repositorio para registrar al usuario.
     *
     * Maneja el resultado con fold: onSuccess limpia errores y ejecuta el callback,
     * onFailure actualiza el estado con el mensaje de error.
     */
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
                    // Registro exitoso: limpiamos errores y ejecutamos callback
                    _uiState.value = _uiState.value.copy(errorMessage = null)
                    onSuccess() // Navegar a otra pantalla o mostrar diálogo
                },
                onFailure = { ex ->
                    // Error en registro: mostramos mensaje
                    _uiState.value = _uiState.value.copy(errorMessage = ex.message)
                }
            )
        }
    }
}
