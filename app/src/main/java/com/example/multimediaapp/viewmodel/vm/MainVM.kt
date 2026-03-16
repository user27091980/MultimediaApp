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

    private val repo = MainRepo(RetrofitModule.mainApi)
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val BASE_URL = "http://10.0.2.2:5131" // base de tu servidor

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val bands = repo.getBands().map { dto ->
                    // Aseguramos que la URL de la imagen sea completa
                    val imageUrl = if (dto.imageBand.startsWith("http")) {
                        dto.imageBand
                    } else {
                        "$BASE_URL${dto.imageBand}"
                    }
                    dto.copy(imageBand = imageUrl)
                }

                _uiState.update {
                    it.copy(
                        mainBands = bands,
                        isLoading = false,
                        error = if (bands.isEmpty()) "No se encontraron bandas" else null
                    )
                }

                Log.d("DEBUG_API", "Carga exitosa: ${bands.size} elementos")

            } catch (e: Exception) {
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