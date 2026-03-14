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

    // USAMOS EL MÓDULO CENTRAL:
    // Así evitas crear un nuevo cliente Retrofit cada vez que abres esta pantalla.
    private val repo = MainRepo(RetrofitModule.mainApi)

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val bands = repo.getBands()

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