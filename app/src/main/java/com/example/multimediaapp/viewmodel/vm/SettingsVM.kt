package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel encargado de gestionar la configuración de la aplicación.
 *
 * Gestiona el estado de las preferencias del usuario y las expone mediante
 * [StateFlow] para que la UI pueda observar cambios de forma reactiva.
 *
 * Ejemplo de configuración:
 * - Modo oscuro
 */
class SettingsVM : ViewModel() {

    /**
     * Estado interno mutable de la configuración.
     */
    private val _uiState = MutableStateFlow(SettingsUiState(darkMode = false))

    /**
     * Estado observable expuesto a la UI.
     */
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    /**
     * Actualiza el estado del modo oscuro.
     *
     * @param enabled Indica si el modo oscuro está activado.
     * - true: modo oscuro activado
     * - false: modo oscuro desactivado
     */
    fun onDarkModeChange(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(darkMode = enabled)
    }
}
/**
 * SettingsVM (ViewModel de configuración):
 *
 * Este ViewModel se encarga de gestionar el estado de la pantalla de ajustes
 * de la aplicación, como por ejemplo el modo oscuro.
 *
 * FORMA PARTE DE MVVM:
 *
 * - Separa la lógica de negocio de la UI.
 * - Mantiene el estado de configuración centralizado.
 * - Expone los datos de forma reactiva mediante StateFlow.
 *
 * ESTADO:
 *
 * _uiState:
 * - MutableStateFlow interno.
 * - Contiene un objeto SettingsUiState con:
 *      • darkMode (modo oscuro activado o no)
 *      • appVersion (versión de la app)
 *
 * uiState:
 * - StateFlow público (solo lectura).
 * - La UI lo observa con collectAsState().
 * - Cada cambio provoca recomposición automática en Compose.
 *
 * FUNCIONAMIENTO:
 *
 * 1. La UI observa uiState.
 *
 * 2. Cuando el usuario interactúa (por ejemplo, activa el switch):
 *      → se llama a onDarkModeChange().
 *
 * 3. El ViewModel actualiza el estado:
 *      → _uiState.value = _uiState.value.copy(...)
 *
 * 4. La UI se recomponen automáticamente con el nuevo valor.
 *
 * FUNCIÓN PRINCIPAL:
 *
 * onDarkModeChange(enabled: Boolean):
 *
 * - Recibe el nuevo estado del switch.
 * - Actualiza la propiedad darkMode.
 * - Usa copy() para mantener la inmutabilidad del estado.
 *
 * BENEFICIOS:
 *
 * - Estado centralizado y fácil de mantener.
 * - UI reactiva sin necesidad de callbacks complejos.
 * - Separación clara entre lógica y presentación.
 *
 * NOTAS:
 *
 * - Actualmente el estado es solo en memoria (no persistente).
 * - Se podría mejorar guardando el modo oscuro en:
 *      • DataStore
 *      • SharedPreferences
 *
 * - StateFlow garantiza que la UI siempre tenga el valor más actualizado.
 */