package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.session.DataStoreManager
import com.example.multimediaapp.viewmodel.uistate.SettingsUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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
class SettingsVM(private val dataStore: DataStoreManager) : ViewModel() {

    // 1. Obtenemos el ID del usuario actual de la sesión
    private val currentUser = dataStore.userFlow.map { it?.id ?: "guest" }

    // 2. El UIState reacciona al usuario logueado
    val uiState: StateFlow<SettingsUiState> = currentUser.flatMapLatest { userId ->
        dataStore.getDarkModeFlow(userId).map { SettingsUiState(darkMode = it) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsUiState())

    fun onDarkModeChange(enabled: Boolean) {
        viewModelScope.launch {
            // Obtenemos el ID actual y guardamos su preferencia
            val userId = dataStore.userFlow.firstOrNull()?.id ?: "guest"
            dataStore.saveDarkMode(userId, enabled)
        }
    }
}
/**
 * SettingsVMFactory:
 * NECESARIA para que el NavGraph y la MainActivity puedan
 * inyectar el DataStoreManager en el ViewModel.
 */
class SettingsVMFactory(private val dataStore: DataStoreManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsVM(dataStore) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}