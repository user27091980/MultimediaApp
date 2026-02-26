package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.viewmodel.uistate.DialogUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


// ViewModel encargado de manejar el estado de un diálogo en la UI
class DialogVM : ViewModel(){
    // Estado interno mutable del diálogo (solo modificable dentro del ViewModel)
    private val _uiState = MutableStateFlow(DialogUiState())
    // Estado expuesto a la UI como inmutable
    // La vista solo puede observarlo, no modificarlo
    val uiState: StateFlow<DialogUiState> = _uiState.asStateFlow()
    // Muestra el diálogo cambiando la propiedad isVisible a true
    fun showDialog() {
        _uiState.value = _uiState.value.copy(isVisible = true)
    }
    // Oculta el diálogo cambiando la propiedad isVisible a false
    fun hideDialog() {
        _uiState.value = _uiState.value.copy(isVisible = false)
    }
    // Acción de confirmación:
    // - Marca el diálogo como confirmado
    // - Lo oculta después de confirmar
    fun confirmAction() {
        _uiState.value = _uiState.value.copy(
            isConfirmed = true,
            isVisible = false
        )
    }

}