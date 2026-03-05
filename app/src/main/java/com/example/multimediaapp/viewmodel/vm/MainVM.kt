package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.data.repository.MainRepo
import com.example.multimediaapp.model.MainDTO
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
data class MainUiState(
    val imageBands: List<MainDTO> = emptyList()
)

class MainVM : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    // Función para cargar datos (fake para preview y pruebas)
    fun loadData() {
        viewModelScope.launch {
            // Aquí simulas obtener datos del repositorio
            val fakeBands = listOf(
                MainDTO(id = "1", imageBand = "https://via.placeholder.com/150"),
                MainDTO(id = "2", imageBand = "https://via.placeholder.com/150"),
                MainDTO(id = "3", imageBand = "https://via.placeholder.com/150")
            )
            _uiState.value = MainUiState(mainBands =  fakeBands)
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