package com.example.multimediaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.model.BandDTO
//import com.example.multimediaapp.network.ApiService
import com.example.multimediaapp.network.BandApiService
import com.example.multimediaapp.retrofit.RetrofitModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar BandDTO usando BandsRepo.
 * Expone datos de manera reactiva con StateFlow.
 */
class BandVM : ViewModel() {

    // Usamos el repositorio centralizado
    private val repository = BandsRepo(RetrofitModule.bandApi)
    //carga las bandas nada más abrirse
    init {
        loadAllBands()
    }

    private val _bandsState = MutableStateFlow<List<BandDTO>>(emptyList())
    val bandsState: StateFlow<List<BandDTO>> = _bandsState.asStateFlow()

    private val _selectedBand = MutableStateFlow<BandDTO?>(null)
    val selectedBand: StateFlow<BandDTO?> = _selectedBand.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // --- Funciones CRUD ---

    fun loadAllBands() {
        viewModelScope.launch {
            try {
                _error.value = null
                val bands = repository.getBands()
                _bandsState.value = bands
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }

    fun createBand(band: BandDTO) {
        viewModelScope.launch {
            try {
                val created = repository.createBand(band)
                created?.let {
                    _bandsState.value = _bandsState.value + it
                }
            } catch (e: Exception) {
                _error.value = "Error al crear: ${e.localizedMessage}"
            }
        }
    }

    fun updateBand(id: String, band: BandDTO) {
        viewModelScope.launch {
            try {
                val updated = repository.updateBand(id, band)
                updated?.let { bandUpdated ->
                    _bandsState.value =
                        _bandsState.value.map { if (it.id == id) bandUpdated else it }
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteBand(id: String) {
        viewModelScope.launch {
            try {
                val deleted = repository.deleteBand(id)
                if (deleted) {
                    _bandsState.value = _bandsState.value.filter { it.id != id }
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
