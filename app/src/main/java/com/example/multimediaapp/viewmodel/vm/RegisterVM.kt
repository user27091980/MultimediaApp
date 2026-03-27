package com.example.multimediaapp.viewmodel.vm

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UserInfoRepo
import com.example.multimediaapp.retrofit.RetrofitModule

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
/**
 * ViewModel encargado de la pantalla de registro de usuario.
 *
 * Gestiona:
 * - El estado reactivo del formulario
 * - La validación de los campos
 * - La comunicación con el repositorio de usuarios
 *
 * Utiliza [AndroidViewModel] para disponer del contexto de la aplicación,
 * necesario para operaciones relacionadas con almacenamiento o sesión.
 *
 * @property application Contexto de la aplicación.
 */
class RegisterVM(application: Application) : AndroidViewModel(application) {

    private val repo = UserInfoRepo(RetrofitModule.userInfoApi) // API inyectada

    private val _uiState = MutableStateFlow(
        RegisterFormUiState(
            errorMessage = null,
            user = "",
            lastName = "",
            email = "",
            pass = "",
            name = "",
            country = "",
            isLoading = false
        )
    )
    val uiState: StateFlow<RegisterFormUiState> = _uiState.asStateFlow()

    fun onUserChange(newUser: String) = _uiState.update { it.copy(user = newUser, errorMessage = null) }
    fun onEmailChange(newEmail: String) = _uiState.update { it.copy(email = newEmail, errorMessage = null) }
    fun onPassChange(newPass: String) = _uiState.update { it.copy(pass = newPass, errorMessage = null) }
    fun onNameChange(newName: String) = _uiState.update { it.copy(name = newName, errorMessage = null) }
    fun onLastNameChange(newLastname: String) = _uiState.update { it.copy(lastName = newLastname, errorMessage = null) }
    fun onCountryChange(newCountry: String) = _uiState.update { it.copy(country = newCountry, errorMessage = null) }

    fun validateFields(onSuccess: () -> Unit) {
        val state = _uiState.value

        val error = when {
            state.pass.length < 4 -> "La contraseña debe tener al menos 4 caracteres"
            state.user.isBlank() || state.name.isBlank() || state.lastName.isBlank() -> "Usuario, nombre y apellido son obligatorios"
            else -> null
        }

        if (error != null) {
            _uiState.update { it.copy(errorMessage = error) }
        } else {
            registerUser(
                user = state.user,
                email = state.email,
                pass = state.pass,
                name = state.name,
                lastName = state.lastName,
                country = state.country,
                onSuccess = onSuccess
            )
        }
    }

    private fun registerUser(
        user: String,
        email: String,
        pass: String,
        name: String,
        lastName: String,
        country: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            repo.register(
                user = user,
                email = email,
                pass = pass,
                name = name,
                lastName = lastName,
                country = country
            ).fold(
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