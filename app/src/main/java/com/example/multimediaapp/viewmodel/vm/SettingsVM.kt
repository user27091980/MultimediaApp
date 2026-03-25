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