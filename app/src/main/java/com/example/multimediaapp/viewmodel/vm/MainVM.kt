package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.MainRepo
import com.example.multimediaapp.network.MultimediaApiService
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

class MainVM (api: MultimediaApiService) : ViewModel() {

    // instancia real del repositorio
    private val repo = MainRepo(api)
    private val _uiState = MutableStateFlow(MainUiState(isLoading = true))
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        loadData()
    }


    // Función para cargar datos (fake para preview y pruebas)
    fun loadData() {
        viewModelScope.launch {
            try {
                val bands = repo.getBands() // aquí luego será suspend si usas Retrofit
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