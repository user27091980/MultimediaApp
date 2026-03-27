package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel encargado de gestionar la configuración de la aplicación.
 *
 * Funciones principales:
 * - Mantener el estado reactivo de las preferencias de usuario.
 * - Exponer cambios a la UI mediante [StateFlow] para recomposición automática en Compose.
 *
 * Ejemplo de preferencias gestionadas:
 * - Modo oscuro
 *
 * Patrón MVVM:
 * - La UI observa [uiState] y se actualiza automáticamente al cambiar las preferencias.
 * - Toda la lógica de cambios de configuración queda encapsulada en el ViewModel.
 */
class SettingsVM : ViewModel() {

    /** Estado interno mutable de la configuración */
    private val _uiState = MutableStateFlow(SettingsUiState(darkMode = false))

    /** Estado observable que la UI puede observar */
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    /**
     * Cambia el estado del modo oscuro.
     *
     * @param enabled Indica si el modo oscuro está activado:
     * - true: modo oscuro activado
     * - false: modo oscuro desactivado
     *
     * Este cambio actualiza [_uiState], lo que provoca que la UI se recomponda automáticamente.
     */
    fun onDarkModeChange(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(darkMode = enabled)
    }
}