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
 * MainVM:
 * ViewModel encargado de la pantalla principal de la app,
 * gestionando la lista de bandas que se muestran en MainScreen.
 *
 * Usa StateFlow para exponer los datos de manera reactiva a la UI.
 */
class MainVM : ViewModel() {
    // Repositorio que obtiene datos desde la API (Retrofit)
    private val repo = MainRepo(RetrofitModule.mainApi)

    // Estado interno mutable (solo el ViewModel lo puede modificar)s
    private val _uiState = MutableStateFlow(MainUiState())
    // Estado público inmutable (la UI solo puede observar)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    // URL base del backend (necesaria para completar rutas relativas de imágenes)
    private val BASE_URL = "http://10.0.2.2:5131/" // base de tu servidor
    // Se ejecuta al crear el ViewModel
    init {
        loadData()
    }
    /**
     * Carga los datos desde la API.
     * Maneja:
     * - loading
     * - éxito
     * - error
     */
    fun loadData() {
        // Lanza una corrutina (no bloquea la UI)
        viewModelScope.launch {
            // Activa estado de carga y limpia errores previos
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // Llamada al repositorio para obtener bandas
                val bands = repo.getBands()

                // Actualiza el estado con los datos obtenidos
                _uiState.update {
                    it.copy(
                        mainBands = bands,
                        isLoading = false,
                        // Si la lista está vacía se muestra mensaje de error
                        error = if (bands.isEmpty()) "No se encontraron bandas" else null
                    )
                }
                // Log para debugging
                Log.d("DEBUG_API", "Carga exitosa: ${bands.size} elementos")

            } catch (e: Exception) {
                // Log de error detallado
                Log.e("DEBUG_API", "Error en loadData: ${e.message}", e)
                // Actualiza estado con error
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