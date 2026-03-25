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
/**
 * LoginRegVM (ViewModel de Login / Registro):
 *
 * Este ViewModel se encarga de gestionar los eventos de la pantalla
 * inicial de autenticación (Login / Registro).
 *
 * OBJETIVO:
 *
 * - Separar la lógica de navegación de la UI.
 * - Gestionar eventos de un solo uso (one-shot events).
 * - Mantener una arquitectura limpia basada en MVVM.
 *
 * ARQUITECTURA:
 *
 * UI (Compose)
 *     ↓
 * LoginRegVM
 *     ↓
 * Eventos (SharedFlow)
 *
 * DIFERENCIA CLAVE:
 *
 * - StateFlow → estado persistente (ej: datos, loading, errores)
 * - SharedFlow → eventos únicos (ej: navegación, mensajes)
 *
 * FLUJO DE FUNCIONAMIENTO:
 *
 * 1. La UI observa los eventos:
 *      viewModel.events.collect { event -> ... }
 *
 * 2. Cuando el usuario interactúa:
 *      - click en Login → onLoginClick()
 *      - click en Register → onRegisterClick()
 *
 * 3. El ViewModel emite un evento:
 *      _events.emit(...)
 *
 * 4. La UI reacciona:
 *      - Navega a la pantalla correspondiente
 *
 * VARIABLES:
 *
 * _events:
 * - MutableSharedFlow interno.
 * - Canal de comunicación desde el ViewModel hacia la UI.
 *
 * events:
 * - Versión pública (solo lectura).
 * - La UI se suscribe a este flujo.
 *
 * viewModelScope:
 * - Scope de corrutinas ligado al ciclo de vida del ViewModel.
 * - Evita fugas de memoria.
 *
 * MÉTODOS:
 *
 * onLoginClick():
 * - Emite el evento NavigateToLogin.
 *
 * onRegisterClick():
 * - Emite el evento NavigateToRegister.
 *
 * LoginRegEvent (sealed class):
 *
 * - Representa los posibles eventos de navegación.
 * - Permite un manejo seguro y tipado.
 *
 * TIPOS DE EVENTOS:
 *
 * NavigateToLogin:
 * - Indica que la UI debe navegar a la pantalla de login.
 *
 * NavigateToRegister:
 * - Indica que la UI debe navegar a la pantalla de registro.
 *
 * BENEFICIOS:
 *
 * - Eventos desacoplados de la UI.
 * - Arquitectura limpia.
 * - Evita problemas de recomposición duplicada.
 * - Manejo seguro gracias a sealed class.
 *
 * NOTA:
 *
 * - SharedFlow se usa en lugar de StateFlow porque:
 *   → los eventos no deben persistir en el estado.
 *   → solo deben emitirse una vez.
 */