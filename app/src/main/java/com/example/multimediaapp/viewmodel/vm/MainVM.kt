package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.MainRepo
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de manejar la lógica de la pantalla principal.
 *
 * Forma parte de la arquitectura MVVM:
 * - No contiene lógica de UI.
 * - No accede directamente a la vista.
 * - Se comunica con el Repository.
 * - Expone un StateFlow que la UI observa.
 */

class MainVM : ViewModel() {

    // Repositorio que proporciona los datos
    private val repo = MainRepo()

    // Estado interno mutable
    private val _uiState = MutableStateFlow(MainUiState(isLoading = true))

    // Estado observable por la UI
    val uiState: StateFlow<MainUiState> = _uiState

    // Se ejecuta automáticamente al crear el ViewModel
    init {
        loadData()
    }

    /**
     * Carga las bandas desde el repositorio
     */
    fun loadData() {

        viewModelScope.launch {

            try {

                // Obtener datos del repositorio
                val bands = repo.getBands()

                _uiState.value = _uiState.value.copy(
                    mainBands = bands,
                    isLoading = false,
                    error = null
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }
}
/** Teoría:
 * flujo de la Arquitectura aplicada:
UI (Activity/Compose)
↓
ViewModel
↓
Repository
↓
Datos en memoria
 */