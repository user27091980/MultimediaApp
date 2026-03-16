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

    // Repositorio centralizado que se conecta con el servidor vía Retrofit
    private val repo = MainRepo(RetrofitModule.mainApi)

    // StateFlow interno (_uiState) para mantener el estado de la pantalla
    private val _uiState = MutableStateFlow(MainUiState())

    // Exposición solo lectura del estado a la UI
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    // Al inicializar el ViewModel, cargamos los datos automáticamente
    init {
        loadData()
    }

    /**
     * loadData():
     * Función que consulta al repositorio la lista de bandas y actualiza el estado.
     * - Maneja isLoading para mostrar un indicador mientras se carga.
     * - Maneja errores de red y vacíos.
     */
    fun loadData() {
        viewModelScope.launch {
            // Indicamos que estamos cargando y reseteamos cualquier error previo
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // Llamada suspend a la API para obtener las bandas
                val bands = repo.getBands()

                // Si no hay bandas, mostramos mensaje de error en UI
                if (bands.isEmpty()) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "No se encontraron bandas disponibles"
                        )
                    }
                } else {
                    // Si se obtienen datos, actualizamos la lista y quitamos isLoading/error
                    _uiState.update {
                        it.copy(
                            mainBands = bands,
                            isLoading = false,
                            error = null
                        )
                    }
                    Log.d("DEBUG_API", "Carga exitosa: ${bands.size} elementos")
                }

            } catch (e: Exception) {
                // Captura de excepciones: problemas de red, parsing o errores inesperados
                Log.e("DEBUG_API", "Error en loadData: ${e.message}", e)

                // Actualizamos el estado con el error para que la UI pueda mostrarlo
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Error de conexión desconocido"
                    )
                }
            }
        }
    }
}