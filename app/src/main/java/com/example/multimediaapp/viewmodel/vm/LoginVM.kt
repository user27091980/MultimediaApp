package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.LoginRepo
import com.example.multimediaapp.session.DataStoreManager
import com.example.multimediaapp.viewmodel.uistate.LoginUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Eventos que el ViewModel puede enviar a la UI.
 *
 * - NavigateToHome: El login fue exitoso y la UI debe navegar a la pantalla principal.
 * - ShowError: Ocurrió un error durante el login, la UI debe mostrar un mensaje.
 */
sealed class LoginEvent {
    object NavigateToHome : LoginEvent()
    data class ShowError(val message: String) : LoginEvent()
}

/**
 * ViewModel de la pantalla de login.
 *
 * Responsabilidades:
 * - Mantener el estado actual del formulario ([LoginUiState]).
 * - Validar los campos de usuario/email y contraseña.
 * - Ejecutar el login mediante el repositorio ([LoginRepo]).
 * - Guardar preferencias locales con [DataStoreManager].
 * - Emitir eventos hacia la UI usando un [Channel].
 *
 * Patrón MVVM:
 * - La UI observa [uiState] para actualizarse automáticamente.
 * - Los eventos de un solo uso se envían a través de [_events].
 *
 * @param dataStore Manager para guardar datos persistentes del usuario.
 * @param repo Repositorio para manejar la autenticación.
 */
class LoginVM(
    val dataStore: DataStoreManager,
    private val repo: LoginRepo
) : ViewModel() {

    /** Estado interno mutable del formulario */
    private val _uiState = MutableStateFlow(LoginUiState())

    /** Estado observable del formulario expuesto a la UI */
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    /** Canal de eventos hacia la UI (navegación y errores) */
    private val _events = Channel<LoginEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    /** Actualiza el valor del usuario/email en el estado */
    fun onUserChange(user: String) {
        _uiState.update { it.copy(user = user) }
    }

    /** Actualiza el valor de la contraseña en el estado */
    fun onPasswordChange(pass: String) {
        _uiState.update { it.copy(password = pass) }
    }

    /** Alterna la visibilidad de la contraseña en el campo de texto */
    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    /**
     * Valida los campos del login.
     *
     * - Retorna true si usuario/email y contraseña no están vacíos.
     * - Si algún campo está vacío, envía un evento [ShowError].
     */
    fun validateFieldsLogin(): Boolean {
        val state = _uiState.value
        return if (state.user.isBlank() || state.password.isBlank()) {
            viewModelScope.launch {
                _events.send(LoginEvent.ShowError("Usuario/Email y contraseña son requeridos"))
            }
            false
        } else true
    }

    /**
     * Ejecuta el login.
     *
     * Flujo:
     * 1. Llama al repositorio para autenticar al usuario.
     * 2. Si es exitoso, guarda el email en [DataStoreManager].
     * 3. Envía [NavigateToHome] para indicar que la UI debe cambiar de pantalla.
     * 4. Si ocurre un error, envía [ShowError] con el mensaje correspondiente.
     */
    fun login() {
        val state = _uiState.value
        viewModelScope.launch {
            try {
                val userEntity = repo.login(state.user, state.password)
                dataStore.saveUserEmail(userEntity.name)
                _events.send(LoginEvent.NavigateToHome)
            } catch (e: Exception) {
                _events.send(LoginEvent.ShowError(e.message ?: "Error desconocido"))
            }
        }
    }
}

/**
 * Factory para crear instancias de [LoginVM] con parámetros personalizados.
 *
 * Necesario porque [LoginVM] no tiene constructor vacío.
 * Se utiliza en conjunto con ViewModelProvider para inyectar dependencias.
 *
 * @param dataStore DataStoreManager para guardar preferencias.
 * @param repo Repositorio de autenticación de usuarios.
 */
class LoginVMFactory(
    private val dataStore: DataStoreManager,
    private val repo: LoginRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginVM(dataStore, repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}