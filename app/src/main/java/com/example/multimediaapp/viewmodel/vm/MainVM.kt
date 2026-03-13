package com.example.multimediaapp.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.MainRepo
import com.example.multimediaapp.network.MainApiService
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainVM : ViewModel() {

    // CORRECCIÓN/MEJORA:
    // Usamos el método .create() que definimos en el Companion Object de la interfaz
    // o la instancia que tengas en tu RetrofitModule.
    private val mainApi = MainApiService.create()
    private val repo = MainRepo(mainApi)

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    // Ejecutamos la carga inicial al instanciar el ViewModel
    init {
        loadData()
    }

    /**
     * Carga los datos de las bandas principales.
     */
    fun loadData() {
        viewModelScope.launch {
            // 1. Estado de carga activo y reset de error
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // 2. Llamada al repositorio
                val bands = repo.getBands()

                // 3. Verificamos si la lista llegó vacía para manejarlo en la UI si quieres
                if (bands.isEmpty()) {
                    _uiState.update {
                        it.copy(isLoading = false, error = "No se encontraron bandas disponibles")
                    }
                } else {
                    _uiState.update {
                        it.copy(mainBands = bands, isLoading = false)
                    }
                    Log.d("DEBUG_API", "Carga exitosa: ${bands.size} elementos")
                }

            } catch (e: Exception) {
                // 4. Captura de errores (Red, Timeout, etc.)
                Log.e("DEBUG_API", "Error en loadData: ${e.message}", e)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Error de conexión: Verifica que el servidor esté activo"
                    )
                }
            }
        }
    }
}