package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * ViewModel que maneja el estado de un diálogo de confirmación.
 *
 * Este ViewModel se encarga de:
 * Saber si el diálogo debe mostrarse o no.
 * Ocultar o mostrar el diálogo.
 * Confirmar acciones desde el diálogo.
 */
class DialogVM : ViewModel() {
    // StateFlow interno que mantiene el estado de visibilidad del diálogo.
    // false = diálogo oculto, true = diálogo visible
    private val _uiState = MutableStateFlow(false)
    // Exponemos el estado como StateFlow inmutable para que la UI lo observe
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()
    /**
     * Muestra el diálogo.
     * Cambia el valor del StateFlow a true.
     * Compose observará este cambio y mostrará el AlertDialog.
     */
    fun showDialog() {
        _uiState.value = true
    }
    /**
     * Oculta el diálogo.
     * Cambia el valor del StateFlow a false.
     * Compose observará este cambio y ocultará el AlertDialog.
     */
    fun hideDialog() {
        _uiState.value = false
    }
    /**
     * Acción de confirmación desde el diálogo.
     *
     * Por ahora solo oculta el diálogo, pero aquí podrías
     * disparar lógica adicional como enviar un evento a otro ViewModel.
     */
    fun confirmAction() {
        _uiState.value = false
    }
}
