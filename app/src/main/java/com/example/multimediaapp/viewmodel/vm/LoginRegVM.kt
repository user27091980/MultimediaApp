package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de Login/Registro.
 *
 * Se encarga de:
 * - Mantener la lógica de navegación separada de la UI.
 * - Emitir eventos de un solo uso (como navegación) usando [SharedFlow].
 * - Gestionar estados de UI simples si fueran necesarios.
 *
 * Uso recomendado:
 * - La UI observa `events` mediante `collectAsState` o `LaunchedEffect` en Compose.
 * - Cuando el usuario pulsa "Login" o "Register", se emite un evento que la UI interpreta.
 *
 * Autor: Andrés
 */
class LoginRegVM : ViewModel() {

    /**
     * MutableSharedFlow interno para emitir eventos de navegación de un solo uso.
     * Usamos SharedFlow porque los eventos no forman parte del estado persistente.
     */
    private val _events = MutableSharedFlow<LoginRegEvent>()

    /**
     * SharedFlow público inmutable que la UI puede observar.
     * Evita que la UI pueda modificar directamente los eventos.
     */
    val events = _events.asSharedFlow()

    // ----------------------
    // FUNCIONES PÚBLICAS
    // ----------------------

    /**
     * Llamada cuando el usuario pulsa el botón "Login".
     * Emite un evento [NavigateToLogin] al flujo para que la UI navegue a la pantalla de login.
     */
    fun onLoginClick() {
        viewModelScope.launch {
            _events.emit(LoginRegEvent.NavigateToLogin)
        }
    }

    /**
     * Llamada cuando el usuario pulsa el botón "Register".
     * Emite un evento [NavigateToRegister] al flujo para que la UI navegue a la pantalla de registro.
     */
    fun onRegisterClick() {
        viewModelScope.launch {
            _events.emit(LoginRegEvent.NavigateToRegister)
        }
    }
}

/**
 * Sealed class que define los posibles eventos de navegación desde la pantalla Login/Registro.
 *
 * - NavigateToLogin: Indica que se debe navegar a la pantalla de login.
 * - NavigateToRegister: Indica que se debe navegar a la pantalla de registro.
 *
 * Ser sealed permite un manejo seguro y exhaustivo de los eventos en la UI,
 * evitando errores de tipo y facilitando el pattern matching.
 */
sealed class LoginRegEvent {
    object NavigateToLogin : LoginRegEvent()
    object NavigateToRegister : LoginRegEvent()
}