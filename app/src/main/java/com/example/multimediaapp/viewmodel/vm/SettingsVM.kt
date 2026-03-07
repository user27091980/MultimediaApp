package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * ViewModel encargado de gestionar la configuración de la app (Settings).
 *
 * - Expone un StateFlow observable para la UI.
 * - Mantiene el estado de las opciones de usuario.
 * - Ideal para MVVM limpio y reactivo.
 */
class SettingsVM : ViewModel() {

    /** Estado interno mutable */
    private val _uiState = MutableStateFlow(SettingsUiState(darkMode = false))
    /** Estado expuesto como solo lectura */
    val uiState: StateFlow<SettingsUiState> = _uiState

    /**
     * Cambia el modo oscuro de la aplicación.
     *
     * @param enabled true si el modo oscuro está activado, false si está desactivado.
     */
    fun onDarkModeChange(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(darkMode = enabled)

    }
}