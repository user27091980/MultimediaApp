package com.example.multimediaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimediaapp.data.repository.BandsRepo
import com.example.multimediaapp.model.BandDTO
import com.example.multimediaapp.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar BandDTO usando BandsRepo.
 * Expone datos de manera reactiva con StateFlow.
 */
open class BandVM(
    private val repository: BandsRepo
) : ViewModel(){
    val vm = BandVM(BandsRepo(ApiService.create()))
    // Lista de bandas observable
    private val _bandsState = MutableStateFlow<List<BandDTO>>(emptyList())
    val bandsState: StateFlow<List<BandDTO>> get() = _bandsState

    // Banda seleccionada
    private val _selectedBand = MutableStateFlow<BandDTO?>(null)
    val selectedBand: StateFlow<BandDTO?> get() = _selectedBand

    // Estado de error
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    // --- Funciones CRUD ---

    fun loadAllBands() {
        viewModelScope.launch {
            try {
                val bands = repository.getBands()
                _bandsState.value = bands
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun fetchBandById(id: String) {
        viewModelScope.launch {
            try {
                val band = repository.getBandById(id)
                _selectedBand.value = band
            } catch (e: Exception) {
                _error.value = e.message
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
                _error.value = e.message
            }
        }
    }

    fun updateBand(id: String, band: BandDTO) {
        viewModelScope.launch {
            try {
                val updated = repository.updateBand(id, band)
                updated?.let { bandUpdated ->
                    _bandsState.value = _bandsState.value.map { if (it.id == id) bandUpdated else it }
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