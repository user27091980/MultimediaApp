package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla Login/Registro
 *
 * Gestiona eventos de navegación y posibles estados de UI.
 * Autor: Andrés
 */
class LoginRegVM : ViewModel() {

    // Eventos de un solo uso (navegación)
    private val _events = MutableSharedFlow<LoginRegEvent>()
    val events = _events.asSharedFlow()

    // -------------------------------------------------
    // EVENTOS
    // -------------------------------------------------

    fun onLoginClick() {
        viewModelScope.launch {
            _events.emit(LoginRegEvent.NavigateToLogin)
        }
    }

    fun onRegisterClick() {
        viewModelScope.launch {
            _events.emit(LoginRegEvent.NavigateToRegister)
        }
    }
}

/**
 * Eventos de navegación de LoginRegScreen
 */
sealed class LoginRegEvent {
    object NavigateToLogin : LoginRegEvent()
    object NavigateToRegister : LoginRegEvent()
}