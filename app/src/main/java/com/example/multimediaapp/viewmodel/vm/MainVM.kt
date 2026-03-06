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

    private val repo = MainRepo
    private val _uiState = MutableStateFlow(MainUiState(isLoading = true))
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        loadData()
    }


    // Función para cargar datos (fake para preview y pruebas)
    fun loadData() {

        val bands = MainRepo.getBands()
        _uiState.value = MainUiState(mainBands = bands)
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