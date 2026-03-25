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
 * Gestiona la obtención y el estado de la lista de bandas mostradas en la UI.
 *
 * Expone el estado mediante [StateFlow] para permitir una UI reactiva.
 *
 * @property repository Repositorio encargado de obtener los datos.
 */
class MainVM : ViewModel() {

    /**
     * Repositorio de datos para obtener la información de las bandas.
     */
    private val repository = MainRepo(RetrofitModule.mainApi)

    /**
     * Estado interno de la UI.
     */
    private val _uiState = MutableStateFlow(MainUiState())

    /**
     * Estado observable de la UI.
     */
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    /**
     * Inicializa la carga de datos al crear el ViewModel.
     */
    init {
        loadData()
    }

    /**
     * Carga la lista de bandas desde el repositorio.
     *
     * Actualiza el estado de la UI:
     * - [MainUiState.isLoading] durante la carga
     * - [MainUiState.mainBands] con los datos obtenidos
     * - [MainUiState.error] en caso de fallo
     */
    fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val bands = repository.getBands()

                _uiState.value = _uiState.value.copy(
                    mainBands = bands,
                    isLoading = false
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}