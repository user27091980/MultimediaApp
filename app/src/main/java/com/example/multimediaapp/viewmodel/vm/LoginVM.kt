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

sealed class LoginEvent {
    object NavigateToHome : LoginEvent()
    data class ShowError(val message: String) : LoginEvent()
}

class LoginVM(
    val dataStore: DataStoreManager,
    private val repo: LoginRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = Channel<LoginEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        // Cargar estado de "Recordar usuario" y el email guardado al iniciar
        observeSavedSession()
    }

    private fun observeSavedSession() {
        viewModelScope.launch {
            // Combinamos los flujos de "recordar" y el "email" para inicializar la UI
            combine(dataStore.rememberUserFlow, dataStore.userFlow) { remember, user ->
                if (remember && user != null) {
                    _uiState.update { it.copy(name = user.email, rememberMe = true) }
                } else if (remember) {
                    // Si solo tenemos el flag de recordar pero no el objeto user completo
                    _uiState.update { it.copy(rememberMe = true) }
                }
            }.first() // Solo nos interesa la primera emisión al cargar la pantalla
        }
    }

    fun onUserChange(name: String) = _uiState.update { it.copy(name = name) }
    fun onPasswordChange(pass: String) = _uiState.update { it.copy(password = pass) }
    fun togglePasswordVisibility() = _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }

    // Nueva función para el Checkbox en la UI
    fun onRememberMeChange(checked: Boolean) = _uiState.update { it.copy(rememberMe = checked) }

    fun login() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val state = _uiState.value
                val userDto = repo.login(state.name, state.password)

                // 1. Guardar preferencia de "Recordar"
                dataStore.saveRememberUser(state.rememberMe)

                // 2. Guardar datos del usuario
                dataStore.saveUser(userDto)

                _events.send(LoginEvent.NavigateToHome)
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message, isLoading = false) }
            }
        }
    }

    fun validateFieldsLogin(): Boolean {
        val state = _uiState.value
        return if (state.name.isBlank() || state.password.isBlank()) {
            viewModelScope.launch {
                _events.send(LoginEvent.ShowError("Campos obligatorios vacíos"))
            }
            false
        } else true
    }
}

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
