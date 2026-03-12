package com.example.multimediaapp.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.entity.toDTO
import com.example.multimediaapp.data.repository.MainRepo
import com.example.multimediaapp.network.ApiService
import com.example.multimediaapp.retrofit.RetrofitModule
import com.example.multimediaapp.viewmodel.uistate.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainVM : ViewModel() {

    // Creamos la instancia de ApiService y la pasamos al repositorio

    private val repo = MainRepo(RetrofitModule.apiService)

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // Usamos RetrofitModule que apunta a http://10.0.2.2
                val response = RetrofitModule.apiService.getMainBands()

                if (response.isSuccessful) {
                    val bands = response.body()?.map { it.toDTO() } ?: emptyList()
                    Log.d("DEBUG_API", "Bandas recibidas: ${bands.size}")
                    _uiState.value = _uiState.value.copy(mainBands = bands, isLoading = false)
                } else {
                    Log.e("DEBUG_API", "Error servidor: ${response.code()}")
                    _uiState.value = _uiState.value.copy(isLoading = false, error = "Error ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("DEBUG_API", "Fallo de conexión", e)
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}