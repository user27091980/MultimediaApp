package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla Login/Registro.
 *
 * Gestiona eventos de navegación y posibles estados de UI.
 * @author: Andrés
 *
 * Uso:
 * Mantener la lógica de navegación separada de la UI.
 * Emitir eventos de un solo uso mediante SharedFlow.
 */
class LoginRegVM : ViewModel() {

    /**
     * MutableSharedFlow interno para emitir eventos de navegación.
     * Se utiliza MutableSharedFlow porque los eventos son de un solo uso.
     */
    private val _events = MutableSharedFlow<LoginRegEvent>()
    /**
     * SharedFlow público inmutable que la UI puede observar.
     * Compose puede recogerlo usando `collectAsState` o `LaunchedEffect`.
     */
    val events = _events.asSharedFlow()

    // FUNCIONES PÚBLICAS
    /**
     * Llamada cuando el usuario pulsa el botón de "Login".
     * Emite el evento NavigateToLogin en el SharedFlow.
     */
    fun onLoginClick() {
        viewModelScope.launch {
            _events.emit(LoginRegEvent.NavigateToLogin)
        }
    }
    /**
     * Llamada cuando el usuario pulsa el botón de "Register".
     * Emite el evento NavigateToRegister en el SharedFlow.
     */
    fun onRegisterClick() {
        viewModelScope.launch {
            _events.emit(LoginRegEvent.NavigateToRegister)
        }
    }
}

/**
* Sealed class que representa los eventos de navegación de la pantalla Login/Registro.
*
* - NavigateToLogin: Navegar a la pantalla de login.
* - NavigateToRegister: Navegar a la pantalla de registro.
*
* Al ser sealed, permite un manejo seguro y tipado de eventos en la UI.
*/
sealed class LoginRegEvent {
    object NavigateToLogin : LoginRegEvent()
    object NavigateToRegister : LoginRegEvent()
}