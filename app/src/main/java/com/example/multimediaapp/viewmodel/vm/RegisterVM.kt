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
class RegisterVM(application: Application) : AndroidViewModel(application) {

    /** Repositorio de usuarios para realizar operaciones de registro */
    private val repo = UserInfoRepo(RetrofitModule.userInfoApi)

    /** Estado interno mutable del formulario */
    private val _uiState = MutableStateFlow(
        RegisterFormUiState(

            lastName = "",
            email = "",
            passwd = "",
            name = "",
            country = "",
            errorMessage = null,
            isLoading = false
        )
    )

    /** Estado observable de la UI */
    val uiState: StateFlow<RegisterFormUiState> = _uiState.asStateFlow()

    // -------------------------
    // Funciones de actualización de campos
    // -------------------------

    /** Actualiza el campo de email y borra el mensaje de error */
    fun onEmailChange(newEmail: String) = _uiState.update { it.copy(email = newEmail, errorMessage = null) }

    /** Actualiza el campo de contraseña y borra el mensaje de error */
    fun onPassChange(newPass: String) = _uiState.update { it.copy(passwd = newPass, errorMessage = null) }

    /** Actualiza el campo de nombre y borra el mensaje de error */
    fun onNameChange(newName: String) = _uiState.update { it.copy(name = newName, errorMessage = null) }

    /** Actualiza el campo de apellido y borra el mensaje de error */
    fun onLastNameChange(newLastname: String) = _uiState.update { it.copy(lastName = newLastname, errorMessage = null) }

    /** Actualiza el campo de país y borra el mensaje de error */
    fun onCountryChange(newCountry: String) = _uiState.update { it.copy(country = newCountry, errorMessage = null) }

    // -------------------------
    // Función de validación de campos
    // -------------------------
    /**
     * Valida los campos del formulario antes de registrar al usuario.
     *
     * - Verifica que la contraseña tenga al menos 4 caracteres.
     * - Verifica que usuario, nombre y apellido no estén vacíos.
     * - Si hay errores, los almacena en [errorMessage].
     * - Si todo es correcto, llama a [registerUser] y ejecuta [onSuccess].
     *
     * @param onSuccess Callback que se ejecuta si la validación y registro son exitosos.
     */
    fun validateFields(onSuccess: () -> Unit) {
        val state = _uiState.value

        val error = when {
            state.passwd.length < 4 -> "La contraseña debe tener al menos 4 caracteres"
            state.name.isBlank() || state.lastName.isBlank() ->
                "Usuario, nombre y apellido son obligatorios"
            else -> null
        }

        if (error != null) {
            _uiState.update { it.copy(errorMessage = error) }
        } else {
            registerUser(

                email = state.email,
                passwd = state.passwd,
                name = state.name,
                lastName = state.lastName,
                country = state.country,
                onSuccess = onSuccess
            )
        }
    }

    // -------------------------
    // Función privada para registrar al usuario
    // -------------------------
    /**
     * Realiza el registro del usuario llamando al repositorio.
     *
     * - Actualiza [isLoading] mientras se realiza la operación.
     * - En caso de éxito, ejecuta [onSuccess] y limpia el estado de carga.
     * - En caso de fallo, almacena el mensaje de error en [errorMessage].
     */
    private fun registerUser(

        email: String,
        passwd: String,
        name: String,
        lastName: String,
        country: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            repo.register(

                email = email,
                passwd = passwd,
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