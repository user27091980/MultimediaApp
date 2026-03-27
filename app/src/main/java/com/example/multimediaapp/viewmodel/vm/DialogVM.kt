package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel que gestiona el estado de un diálogo de confirmación en la UI.
 *
 * Funciones principales:
 * - Controla la visibilidad del diálogo.
 * - Permite acciones de confirmación o cancelación.
 * - Expone el estado mediante [StateFlow] para que la UI reaccione de forma reactiva.
 *
 * Arquitectura:
 * - Sigue el patrón MVVM: la UI observa [uiState] y reacciona a cambios.
 * - La lógica de mostrar/ocultar el diálogo se mantiene centralizada aquí.
 */
class DialogVM : ViewModel() {

    /**
     * Estado interno que representa la visibilidad del diálogo.
     *
     * - true: el diálogo es visible
     * - false: el diálogo está oculto
     */
    private val _uiState = MutableStateFlow(false)

    /**
     * Estado observable del diálogo expuesto a la UI.
     *
     * La UI puede usar `collectAsState()` para renderizar dinámicamente
     * el diálogo cuando cambie este valor.
     */
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()

    /**
     * Muestra el diálogo.
     *
     * Cambia el estado a `true`, provocando que la UI renderice el diálogo.
     */
    fun showDialog() {
        _uiState.value = true
    }

    /**
     * Oculta el diálogo.
     *
     * Cambia el estado a `false`, haciendo que la UI deje de mostrar el diálogo.
     */
    fun hideDialog() {
        _uiState.value = false
    }

    /**
     * Acción de confirmación del diálogo.
     *
     * Actualmente solo oculta el diálogo, pero se puede ampliar
     * para ejecutar lógica adicional (como confirmar datos o enviar información).
     */
    fun confirmAction() {
        _uiState.value = false
    }
}