package com.example.multimediaapp.viewmodel.vm

import ads_mobile_sdk.ad2.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de manejar el estado de la pantalla principal.
 */
class BandVM : ViewModel() {

    // Estado interno mutable
    private val _uiState = MutableStateFlow(MainUiState())

    // Estado expuesto como solo lectura
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    /**
     * Carga los datos de las bandas.
     * Aquí normalmente llamarías a un Repository o API.
     */
    fun loadData() {

        viewModelScope.launch {

            // Activamos loading
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )
            // Actualizamos el estado con los datos
            _uiState.value = _uiState.value.copy(
                bands = bands,
                isLoading = false
            )

        }
    }
}