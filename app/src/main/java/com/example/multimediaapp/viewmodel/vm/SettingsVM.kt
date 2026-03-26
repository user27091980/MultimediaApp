package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.datastore.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la configuración de la aplicación.
 *
 * Gestiona el estado de las preferencias del usuario y las expone mediante
 * [StateFlow] para que la UI pueda observar cambios de forma reactiva.
 *
 * Ejemplo de configuración:
 * - Modo oscuro
 */
open class SettingsVM(private val dataStore: DataStoreManager) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState> = _uiState

    init {
        // Cargar modo oscuro al iniciar
        viewModelScope.launch {
            dataStore.getDarkMode.collect { darkMode ->
                _uiState.update { it.copy(darkMode = darkMode) }
            }
        }
    }

    fun onDarkModeChange(value: Boolean) {
        _uiState.update { it.copy(darkMode = value) }
        viewModelScope.launch {
            dataStore.saveDarkMode(value)
        }
    }
}

class SettingsVMFactory(private val dataStore: DataStoreManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsVM(dataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

data class SettingsState(
    val darkMode: Boolean = false
)
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