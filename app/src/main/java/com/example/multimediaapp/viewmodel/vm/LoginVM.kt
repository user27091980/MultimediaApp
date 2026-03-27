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

    fun onUserChange(user: String) {
        _uiState.update { it.copy(user = user) }
    }

    fun onPasswordChange(pass: String) {
        _uiState.update { it.copy(password = pass) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun validateFieldsLogin(): Boolean {
        val state = _uiState.value
        return if (state.user.isBlank() || state.password.isBlank()) {
            viewModelScope.launch {
                _events.send(LoginEvent.ShowError("Usuario/Email y contraseña son requeridos"))
            }
            false
        } else true
    }

    fun login() {
        val state = _uiState.value
        viewModelScope.launch {
            try {
                val userEntity = repo.login(state.user, state.password)
                // Guardar email en DataStore
                dataStore.saveUserEmail(userEntity.user)
                _events.send(LoginEvent.NavigateToHome)
            } catch (e: Exception) {
                _events.send(LoginEvent.ShowError(e.message ?: "Error desconocido"))
            }
        }
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