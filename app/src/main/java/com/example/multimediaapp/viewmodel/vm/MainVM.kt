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

class MainVM : ViewModel() {

    // Inyectamos el servicio de Main directamente al repositorio
    private val repo = MainRepo(RetrofitModule.mainApi)

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    /**
     * Carga los datos de las bandas principales.
     * El ViewModel ya no sabe nada de Retrofit ni de Responses, solo de DTOs.
     */
    fun loadData() {
        viewModelScope.launch {
            // 1. Indicamos que estamos cargando y limpiamos errores previos
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // 2. Pedimos los datos al repositorio (que ya devuelve List<MainDTO>)
                val bands = repo.getBands()

                // 3. Actualizamos el estado con los datos recibidos
                _uiState.update {
                    it.copy(mainBands = bands, isLoading = false)
                }
                Log.d("DEBUG_API", "Carga exitosa: ${bands.size} elementos")

            } catch (e: Exception) {
                // 4. Capturamos cualquier error (red, parseo, etc.)
                Log.e("DEBUG_API", "Error en loadData", e)
                _uiState.update {
                    it.copy(isLoading = false, error = "Error al cargar datos: ${e.localizedMessage}")
                }
            }
        }
    }
}