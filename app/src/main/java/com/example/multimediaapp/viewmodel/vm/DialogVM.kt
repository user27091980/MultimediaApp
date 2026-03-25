package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * ViewModel que gestiona el estado de un diálogo de confirmación.
 *
 * Se encarga de controlar la visibilidad del diálogo y las acciones
 * asociadas a su confirmación o cancelación.
 *
 * El estado se expone mediante [StateFlow] para que la UI pueda observar
 * los cambios de forma reactiva.
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
     */
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()

    /**
     * Muestra el diálogo.
     *
     * Cambia el estado a `true`, lo que provoca que la UI renderice el diálogo.
     */
    fun showDialog() {
        _uiState.value = true
    }

    /**
     * Oculta el diálogo.
     *
     * Cambia el estado a `false`, ocultando el diálogo en la UI.
     */
    fun hideDialog() {
        _uiState.value = false
    }

    /**
     * Ejecuta la acción de confirmación del diálogo.
     *
     * Actualmente solo oculta el diálogo, pero puede ampliarse
     * para ejecutar lógica adicional como confirmar acciones del usuario.
     */
    fun confirmAction() {
        _uiState.value = false
    }
}
