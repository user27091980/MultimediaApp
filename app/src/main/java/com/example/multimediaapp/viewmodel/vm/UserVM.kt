package com.example.multimediaapp.viewmodel.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.UserRepo
import com.example.multimediaapp.model.UserDTO
import com.example.multimediaapp.network.UserApiService
import com.example.multimediaapp.viewmodel.uistate.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * UserVM:
 * ViewModel encargado de login y registro.
 */
class UserVM(application: Application) : AndroidViewModel(application) {

    private val repo = UserRepo(UserApiService.create())

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    // --- INPUTS ---
    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPassChange(pass: String) {
        _uiState.update { it.copy(pass = pass, errorMessage = null) }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name, errorMessage = null) }
    }

    fun onLastNameChange(lastName: String) {
        _uiState.update { it.copy(lastName = lastName, errorMessage = null) }
    }

    fun onCountryChange(country: String) {
        _uiState.update { it.copy(country = country, errorMessage = null) }
    }

    // --- REGISTER ---
    fun register(onSuccess: () -> Unit) {
        val state = _uiState.value

        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = repo.register(
                email = state.email,
                pass = state.pass,
                name = state.name,
                lastName = state.lastName,
                country = state.country
            )

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false) }
                    onSuccess()
                },
                onFailure = { ex ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = ex.message ?: "Error en registro"
                        )
                    }
                }
            )
        }
    }

    // --- LOGIN ---
    fun login(onSuccess: (UserDTO) -> Unit) {
        val state = _uiState.value

        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = repo.login(
                email = state.email,
                pass = state.pass
            )

            result.fold(
                onSuccess = { user ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            user = user // 👈 guardamos el usuario logueado
                        )
                    }
                    onSuccess(user)
                },
                onFailure = { ex ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = ex.message ?: "Error en login"
                        )
                    }
                }
            )
        }
    }

    // --- OBTENER USUARIO POR ID ---
    fun loadUserById(id: String) {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            try {
                val response = repo.getUserById(id)

                if (response.isSuccessful && response.body() != null) {
                    _uiState.update {
                        it.copy(
                            user = response.body(),
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            errorMessage = "Error al obtener usuario",
                            isLoading = false
                        )
                    }
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}