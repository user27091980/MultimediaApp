package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel encargado de manejar la lógica de la pantalla principal.
 *
 * Forma parte de la arquitectura MVVM:
 * - No contiene lógica de UI.
 * - No accede directamente a la vista.
 * - Se comunica con el Repository.
 * - Expone un StateFlow que la UI observa.
 */
class MainVM (
    // El repositorio se inyecta por constructor.
    // Es la capa encargada de acceder a los datos.
    private val repository: BandsRepo)
    : ViewModel() {
    /**
    * Estado interno mutable.
    * Solo el ViewModel puede modificarlo.
    */
    private val _uiState = MutableStateFlow(MainUiState())

    /**
     * Estado expuesto como solo lectura.
     * La UI puede observarlo pero no modificarlo.
     */
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    /**
     * Carga los datos de las bandas desde el repositorio.
     *
     * Flujo:
     * 1️⃣ Activa estado de carga (isLoading = true)
     * 2️⃣ Llama al repositorio
     * 3️⃣ Si éxito → actualiza lista
     * 4️⃣ Si error → muestra mensaje
     */
    fun loadData() {
        // Activamos estado de carga y limpiamos errores previos
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null
        )
        // Llamada al repositorio usando callbacks
        repository.readAll(
            // Se ejecuta si la operación fue exitosa
            onSuccess = { bands ->
                // Actualizamos el estado con la lista recibida
                _uiState.value = _uiState.value.copy(
                    bands = bands,
                    isLoading = false
                )
            },
            // Se ejecuta si ocurre un error
            onError = {
                // Actualizamos el estado indicando error
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error cargando bandas"
                )
            }
        )
    }
}
/**
 * flujo de la Arquitectura aplicada:
UI (Activity/Compose)
↓
ViewModel
↓
Repository
↓
Datos en memoria
 */