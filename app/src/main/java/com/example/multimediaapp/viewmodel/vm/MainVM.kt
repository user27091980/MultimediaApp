package com.example.multimediaapp.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.MainRepo
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel de la pantalla principal.
 *
 * Función principal:
 * - Gestionar la obtención de la lista de bandas desde un repositorio.
 * - Mantener el estado de la UI mediante [StateFlow] para una actualización reactiva.
 *
 * Arquitectura:
 * - Sigue el patrón MVVM: la UI observa [uiState] y se actualiza automáticamente.
 * - La lógica de obtención de datos se mantiene separada de la UI.
 *
 * @property repository Repositorio encargado de obtener la información de bandas.
 */
class MainVM : ViewModel() {

    /** Repositorio de datos para obtener la información de las bandas */
    private val repository = MainRepo(RetrofitModule.mainApi)

    /** Estado interno mutable de la UI */
    private val _uiState = MutableStateFlow(MainUiState())

    /** Estado observable de la UI */
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    /** Inicializa la carga de datos al crear el ViewModel */
    init {
        loadData()
    }

    /**
     * Carga la lista de bandas desde el repositorio.
     *
     * Flujo de ejecución:
     * 1. Actualiza el estado a `isLoading = true` para mostrar indicador de carga.
     * 2. Llama a `repository.getBands()` para obtener la lista de bandas.
     * 3. Actualiza `mainBands` con los datos obtenidos y `isLoading = false`.
     * 4. Si ocurre un error, actualiza `error` en el estado y oculta la carga.
     *
     * Beneficios:
     * - UI reactiva: cada cambio en `_uiState` provoca recomposición automática.
     * - Separación clara de responsabilidades: ViewModel maneja datos y lógica, UI solo renderiza.
     */
    fun loadData() {
        viewModelScope.launch {
            try {
                // Indicamos que la carga ha iniciado
                _uiState.value = _uiState.value.copy(isLoading = true)

                // Obtenemos las bandas del repositorio
                val bands = repository.getBands()

                // Actualizamos el estado con los datos obtenidos
                _uiState.value = _uiState.value.copy(
                    mainBands = bands,
                    isLoading = false
                )

            } catch (e: Exception) {
                // Manejo de errores: actualizamos el estado con el mensaje de error
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}