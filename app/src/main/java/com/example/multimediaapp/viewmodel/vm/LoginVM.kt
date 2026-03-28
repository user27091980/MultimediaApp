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

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = Channel<LoginEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onUserChange(name: String) = _uiState.update { it.copy(name = name) }
    fun onPasswordChange(pass: String) = _uiState.update { it.copy(password = pass) }
    fun togglePasswordVisibility() = _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }

    fun validateFieldsLogin(): Boolean {
        val state = _uiState.value
        return if (state.name.isBlank() || state.password.isBlank()) {
            viewModelScope.launch {
                _events.send(LoginEvent.ShowError("Campos obligatorios vacíos"))
            }
            false
        } else true
    }

    fun login() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val state = _uiState.value
                // 1. Llamada al repo
                val userDto = repo.login(state.name, state.password)

                // 2. Guardamos los datos recibidos (ID, Name, Email) en el DataStore
                dataStore.saveUser(userDto)

                // 3. Éxito
                _events.send(LoginEvent.NavigateToHome)
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message, isLoading = false) }
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